package org.sc.gateway.robin;


import com.netflix.loadbalancer.Server;
import org.apache.commons.lang3.StringUtils;
import org.springframework.cloud.alibaba.nacos.ribbon.NacosServer;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class NacosWeightRoundRobin extends WeightRoundRobin{

	public Server choose(List<? extends Server> servers){
		return super.getServer(init(servers));
	}
	
	public synchronized List<WeightServer> init(List<? extends Server> servers){
		List<WeightServer> serverList = servers.stream().map(e -> {
			Map<String, String> metadata = ((NacosServer)e).getMetadata();
			String weight = metadata.get("weight");
			System.out.println(e.getPort()+":"+((NacosServer) e).getMetadata().get("weight"));
			return new WeightServer(e, StringUtils.isEmpty(weight) ? 1 : Integer.valueOf(weight));
		}).collect(Collectors.toList());
		
		boolean update = super.servers.containsAll(serverList);
		
		if (!update) {
			maxWeight = greatestWeight(serverList);
			gcdWeight = greatestCommonDivisor(serverList);
			serverCount = serverList.size();
			super.servers = serverList;
		}
		return serverList;
	}



 
}

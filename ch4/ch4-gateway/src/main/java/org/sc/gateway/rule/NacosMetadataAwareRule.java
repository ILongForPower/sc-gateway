package org.sc.gateway.rule;


import org.sc.gateway.predicate.NacosMetadataAwarePredicate;

public class NacosMetadataAwareRule extends DiscoveryEnabledRule {

    public NacosMetadataAwareRule() {
        super(new NacosMetadataAwarePredicate());
    }
}
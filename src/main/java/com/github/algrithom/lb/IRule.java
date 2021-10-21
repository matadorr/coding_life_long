package com.github.algrithom.lb;

public interface IRule {
    /*
     * choose one alive server from lb.allServers or
     * lb.upServers according to key
     *
     * @return choosen Server object. NULL is returned if none
     *  server is available
     */

    Server choose(Object key);

    void setLoadBalancer(ILoadBalancer lb);

    ILoadBalancer getLoadBalancer();
}

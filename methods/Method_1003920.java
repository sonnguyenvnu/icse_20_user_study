/** 
 * Convenience method to create a new load balancer.
 * @param strategy Strategy to use.
 * @param < K > Backend type.
 * @return A new load balancer.
 */
public static <K>LoadBalancerImpl<K> create(LoadBalancingStrategy<K> strategy){
  return new LoadBalancerImpl<K>(strategy);
}

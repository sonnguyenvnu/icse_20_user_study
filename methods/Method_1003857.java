/** 
 * Creates a registry backed by the given bean factory. <p> <b>Note:</b> Spring ListableBeanFactory API doesn't current support looking up beans with parameterized types. The adapted  {@code Registry} instance doesn't support this because of this limitation.There is a <a href="https://jira.spring.io/browse/SPR-12147">feature request</a> to add the generics functionality to the Spring ListableBeanFactory API.
 * @param beanFactory the bean factory to back the registry
 * @return a registry that retrieves objects from the given bean factory
 */
public static Registry spring(ListableBeanFactory beanFactory){
  return Registry.backedBy(new SpringRegistryBacking(beanFactory));
}

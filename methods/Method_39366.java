/** 
 * Iterates all beans. Iteration occurs over the  {@link #beanNames() snapshot of bean names}.
 */
public void forEachBean(final Consumer<BeanDefinition> beanDefinitionConsumer){
  final Set<String> names=beanNames();
  for (  String beanName : names) {
    BeanDefinition beanDefinition=lookupBeanDefinition(beanName);
    if (beanDefinition != null) {
      beanDefinitionConsumer.accept(beanDefinition);
    }
  }
}

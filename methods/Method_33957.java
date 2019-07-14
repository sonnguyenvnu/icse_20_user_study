/** 
 * Attempts to find the place in the filter chain to insert the spring security oauth filters. Currently, these filters are inserted after the ExceptionTranslationFilter.
 * @param filterChain The filter chain configuration.
 * @return The insert index.
 */
private int insertIndex(List<BeanMetadataElement> filterChain){
  int i;
  for (i=0; i < filterChain.size(); i++) {
    BeanMetadataElement filter=filterChain.get(i);
    if (filter instanceof BeanDefinition) {
      String beanName=((BeanDefinition)filter).getBeanClassName();
      if (beanName.equals(ExceptionTranslationFilter.class.getName())) {
        return i + 1;
      }
    }
  }
  return filterChain.size();
}

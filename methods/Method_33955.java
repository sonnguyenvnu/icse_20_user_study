@SuppressWarnings({"unchecked"}) protected static String findDefaultFilterChainBeanId(ParserContext parserContext){
  BeanDefinition filterChainList=parserContext.getRegistry().getBeanDefinition(BeanIds.FILTER_CHAINS);
  List<BeanReference> filterChains=(List<BeanReference>)filterChainList.getPropertyValues().getPropertyValue("sourceList").getValue();
  return filterChains.get(filterChains.size() - 1).getBeanName();
}

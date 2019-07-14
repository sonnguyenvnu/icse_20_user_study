@SuppressWarnings({"unchecked"}) public static List<BeanMetadataElement> findFilterChain(ParserContext parserContext,String explicitRef){
  String filterChainRef=explicitRef;
  if (!StringUtils.hasText(filterChainRef)) {
    filterChainRef=findDefaultFilterChainBeanId(parserContext);
  }
  return (List<BeanMetadataElement>)parserContext.getRegistry().getBeanDefinition(filterChainRef).getConstructorArgumentValues().getArgumentValue(1,List.class).getValue();
}

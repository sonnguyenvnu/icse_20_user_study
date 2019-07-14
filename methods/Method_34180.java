public static BeanDefinition createSecurityMetadataSource(Element element,ParserContext pc){
  List<Element> filterPatterns=DomUtils.getChildElementsByTagName(element,"url");
  if (filterPatterns.isEmpty()) {
    return null;
  }
  String patternType=element.getAttribute("path-type");
  if (!StringUtils.hasText(patternType)) {
    patternType="ant";
  }
  MatcherType matcherType=MatcherType.valueOf(patternType);
  ManagedMap<BeanDefinition,BeanDefinition> invocationDefinitionMap=new ManagedMap<BeanDefinition,BeanDefinition>();
  for (  Element filterPattern : filterPatterns) {
    String path=filterPattern.getAttribute("pattern");
    if (!StringUtils.hasText(path)) {
      pc.getReaderContext().error("pattern attribute cannot be empty or null",filterPattern);
    }
    String method=filterPattern.getAttribute("httpMethod");
    if (!StringUtils.hasText(method)) {
      method=null;
    }
    String access=filterPattern.getAttribute("resources");
    if (StringUtils.hasText(access)) {
      BeanDefinition matcher;
      if (createMatcherMethod4x != null) {
        matcher=(BeanDefinition)ReflectionUtils.invokeMethod(createMatcherMethod4x,matcherType,pc,path,method);
      }
 else {
        matcher=(BeanDefinition)ReflectionUtils.invokeMethod(createMatcherMethod3x,matcherType,path,method);
      }
      if (access.equals("none")) {
        invocationDefinitionMap.put(matcher,BeanDefinitionBuilder.rootBeanDefinition(Collections.class).setFactoryMethod("emptyList").getBeanDefinition());
      }
 else {
        BeanDefinitionBuilder attributeBuilder=BeanDefinitionBuilder.rootBeanDefinition(SecurityConfig.class);
        attributeBuilder.addConstructorArgValue(access);
        attributeBuilder.setFactoryMethod("createListFromCommaDelimitedString");
        if (invocationDefinitionMap.containsKey(matcher)) {
          pc.getReaderContext().warning("Duplicate URL defined: " + path + ". The original attribute values will be overwritten",pc.extractSource(filterPattern));
        }
        invocationDefinitionMap.put(matcher,attributeBuilder.getBeanDefinition());
      }
    }
  }
  BeanDefinitionBuilder fidsBuilder=BeanDefinitionBuilder.rootBeanDefinition(DefaultFilterInvocationSecurityMetadataSource.class);
  fidsBuilder.addConstructorArgValue(invocationDefinitionMap);
  fidsBuilder.getRawBeanDefinition().setSource(pc.extractSource(element));
  return fidsBuilder.getBeanDefinition();
}

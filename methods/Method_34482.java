public HystrixCollapser.Setter buildCollapserCommandSetter(){
  HystrixCollapserProperties.Setter propSetter=initializeCollapserProperties(collapserProperties);
  return HystrixCollapser.Setter.withCollapserKey(HystrixCollapserKey.Factory.asKey(collapserKey)).andScope(scope).andCollapserPropertiesDefaults(propSetter);
}

public static BeanPropertyMap construct(Collection<SettableBeanProperty> props,boolean caseInsensitive,PropertyName[][] aliases){
  return new BeanPropertyMap(caseInsensitive,props,aliases,true);
}

protected POJOPropertyBuilder _property(Map<String,POJOPropertyBuilder> props,String implName){
  POJOPropertyBuilder prop=props.get(implName);
  if (prop == null) {
    prop=new POJOPropertyBuilder(_config,_annotationIntrospector,_forSerialization,PropertyName.construct(implName));
    props.put(implName,prop);
  }
  return prop;
}

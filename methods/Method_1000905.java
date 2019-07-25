protected POJOPropertyBuilder _property(Map<String,POJOPropertyBuilder> props,PropertyName name){
  String simpleName=name.getSimpleName();
  POJOPropertyBuilder prop=props.get(simpleName);
  if (prop == null) {
    prop=new POJOPropertyBuilder(_config,_annotationIntrospector,_forSerialization,name);
    props.put(simpleName,prop);
  }
  return prop;
}

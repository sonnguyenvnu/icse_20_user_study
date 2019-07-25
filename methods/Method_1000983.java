@Override public JavaType refine(Class<?> rawType,TypeBindings bindings,JavaType superClass,JavaType[] superInterfaces){
  return new CollectionLikeType(rawType,bindings,superClass,superInterfaces,_elementType,_valueHandler,_typeHandler,_asStatic);
}

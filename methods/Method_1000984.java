@Override public JavaType refine(Class<?> rawType,TypeBindings bindings,JavaType superClass,JavaType[] superInterfaces){
  return new CollectionType(rawType,bindings,superClass,superInterfaces,_elementType,_valueHandler,_typeHandler,_asStatic);
}

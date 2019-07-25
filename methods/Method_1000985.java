@Override public JavaType refine(Class<?> rawType,TypeBindings bindings,JavaType superClass,JavaType[] superInterfaces){
  return new MapType(rawType,bindings,superClass,superInterfaces,_keyType,_valueType,_valueHandler,_typeHandler,_asStatic);
}

@Override public JavaType refine(Class<?> rawType,TypeBindings bindings,JavaType superClass,JavaType[] superInterfaces){
  return new ReferenceType(rawType,_bindings,superClass,superInterfaces,_referencedType,_anchorType,_valueHandler,_typeHandler,_asStatic);
}

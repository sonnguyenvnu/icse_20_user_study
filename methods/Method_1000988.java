public static ReferenceType construct(Class<?> cls,TypeBindings bindings,JavaType superClass,JavaType[] superInts,JavaType refType){
  return new ReferenceType(cls,bindings,superClass,superInts,refType,null,null,null,false);
}

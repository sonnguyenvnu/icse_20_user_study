public static ArrayType construct(JavaType componentType,TypeBindings bindings,Object valueHandler,Object typeHandler){
  Object emptyInstance=Array.newInstance(componentType.getRawClass(),0);
  return new ArrayType(componentType,bindings,emptyInstance,valueHandler,typeHandler,false);
}

private static Class<?>[] removeAspectjArgs(Class<?>[] parameterTypes){
  Class<?>[] origParamTypes=new Class[parameterTypes.length - 2];
  System.arraycopy(parameterTypes,1,origParamTypes,0,parameterTypes.length - 2);
  return origParamTypes;
}

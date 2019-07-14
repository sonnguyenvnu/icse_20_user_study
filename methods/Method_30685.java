@NonNull private static Object[] checkParameterTypes(@NonNull Object[] parameterTypes){
  Object[] newParameterTypes=new Object[parameterTypes.length];
  for (int i=0, count=parameterTypes.length; i < count; ++i) {
    Object parameterType=parameterTypes[i];
    if (parameterType instanceof String) {
      newParameterTypes[i]=new ReflectedClass((String)parameterType);
    }
 else     if (parameterType instanceof Class<?> || parameterType instanceof ReflectedClass) {
      newParameterTypes[i]=parameterType;
    }
 else {
      throw new IllegalArgumentException("Parameter type must be a class name, a Class or" + " a ReflectedClass: " + parameterType);
    }
  }
  return newParameterTypes;
}

public static int getGenericTypeIndex(TypeVariable<?>[] typeParameters,final String parameterName){
  for (int i=0; i < typeParameters.length; i++) {
    if (typeParameters[i].getName().equals(parameterName)) {
      return i;
    }
  }
  return -1;
}

private static ArrayList<ParameterSignature> signatures(Class<?>[] parameterTypes,Annotation[][] parameterAnnotations){
  ArrayList<ParameterSignature> sigs=new ArrayList<ParameterSignature>();
  for (int i=0; i < parameterTypes.length; i++) {
    sigs.add(new ParameterSignature(parameterTypes[i],parameterAnnotations[i]));
  }
  return sigs;
}

public static ArrayList<ParameterSignature> signatures(Method method){
  return signatures(method.getParameterTypes(),method.getParameterAnnotations());
}

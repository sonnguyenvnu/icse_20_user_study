public static List<ParameterSignature> signatures(Constructor<?> constructor){
  return signatures(constructor.getParameterTypes(),constructor.getParameterAnnotations());
}

public static String getStringRepresentationOfParamTypes(ImmutableList<OptionalParameterType> optionalParameterTypes){
  final StringBuilder stringBuilder=new StringBuilder();
  for (  OptionalParameterType parameterType : optionalParameterTypes) {
    stringBuilder.append(getStringRepresentationOfParamType(parameterType)).append(". ");
  }
  return stringBuilder.toString();
}

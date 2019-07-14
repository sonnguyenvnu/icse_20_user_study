private static String getStringRepresentationOfOptionalParams(ImmutableList<MethodParamModel> optionalParameters){
  final StringBuilder stringBuilder=new StringBuilder();
  for (  MethodParamModel optionalParameter : optionalParameters) {
    stringBuilder.append(optionalParameter.getTypeName()).append(" ").append(optionalParameter.getName()).append(". ");
  }
  return stringBuilder.toString();
}

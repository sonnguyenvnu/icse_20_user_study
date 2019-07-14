public static String getOptionalParamsError(DelegateMethodDescription delegateMethodDescription){
  StringBuilder stringBuilder=new StringBuilder();
  stringBuilder.append("Not a valid parameter, should be one of the following: ").append(getStringRepresentationOfParamTypes(delegateMethodDescription.optionalParameterTypes));
  if (!delegateMethodDescription.optionalParameters.isEmpty()) {
    stringBuilder.append("Or one of the following, where no annotations should be added to the parameter: ").append(getStringRepresentationOfOptionalParams(delegateMethodDescription.optionalParameters));
  }
  return stringBuilder.toString();
}

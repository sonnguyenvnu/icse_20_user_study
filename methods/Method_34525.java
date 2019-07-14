private String createErrorMsg(Method commandMethod,Method fallbackMethod,String hint){
  return "Incompatible return types. \nCommand method: " + commandMethod + ";\nFallback method: " + fallbackMethod + ";\n" + (StringUtils.isNotBlank(hint) ? "Hint: " + hint : "");
}

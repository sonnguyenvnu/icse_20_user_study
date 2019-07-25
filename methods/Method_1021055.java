public ParameterPrinter append(String name,Object val){
  if (paramIndex++ != 0)   result.append(divider);
  if (val != null && val.getClass().isArray()) {
    result.append(String.format(Constants.PARAMETER_PRINT_FORMAT,name,arrayToString(val)));
  }
 else {
    result.append(String.format(Constants.PARAMETER_PRINT_FORMAT,name,val));
  }
  return this;
}

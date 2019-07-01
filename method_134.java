public static boolean _XXXXX_(ParameterInclude paramInclude,String paramName,boolean defaultValue) throws AxisFault {
  Parameter param=paramInclude.getParameter(paramName);
  return param == null ? defaultValue : JavaUtils.isTrueExplicitly(param.getValue(),defaultValue);
}
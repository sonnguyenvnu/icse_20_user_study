private static String[] getParamsFromHint(String hint,String prefix){
  String param=getParamFromHint(hint,prefix);
  return param != null ? param.split(",") : null;
}

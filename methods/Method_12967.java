public static String getFunctionFromReturnUrl(String url){
  String temp=url.replace(YY_RETURN_DATA,EMPTY_STR);
  String[] functionAndData=temp.split(SPLIT_MARK);
  if (functionAndData.length >= 1) {
    return functionAndData[0];
  }
  return null;
}

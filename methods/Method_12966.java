public static String getDataFromReturnUrl(String url){
  if (url.startsWith(YY_FETCH_QUEUE)) {
    return url.replace(YY_FETCH_QUEUE,EMPTY_STR);
  }
  String temp=url.replace(YY_RETURN_DATA,EMPTY_STR);
  String[] functionAndData=temp.split(SPLIT_MARK);
  if (functionAndData.length >= 2) {
    StringBuilder sb=new StringBuilder();
    for (int i=1; i < functionAndData.length; i++) {
      sb.append(functionAndData[i]);
    }
    return sb.toString();
  }
  return null;
}

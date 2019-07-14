static private String replaceString(String str,int start,int end,String put){
  return str.substring(0,start) + put + str.substring(end,str.length());
}

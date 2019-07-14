private String LPad(String inStr,int maxLen){
  if (inStr.length() >= maxLen)   return inStr.toUpperCase();
  String zeroes=PADCHARS.substring(0,maxLen - inStr.length());
  String retVal=zeroes + inStr;
  return retVal.toUpperCase();
}

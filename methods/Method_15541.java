/** 
 * @param key
 * @param value
 * @param method
 * @param verifyName
 * @return
 * @throws Exception
 */
private String getWhereItem(String key,Object value,RequestMethod method,boolean verifyName) throws Exception {
  Log.d(TAG,"getWhereItem  key = " + key);
  if (key == null || value == null || key.startsWith("@") || key.endsWith("()")) {
    Log.d(TAG,"getWhereItem  key == null || value == null" + " || key.startsWith(@) || key.endsWith(()) >> continue;");
    return null;
  }
  if (key.endsWith("@")) {
    throw new IllegalArgumentException(TAG + ".getWhereItem: ?? " + key + " ????");
  }
  int keyType;
  if (key.endsWith("$")) {
    keyType=1;
  }
 else   if (key.endsWith("~") || key.endsWith("?")) {
    keyType=key.charAt(key.length() - 2) == '*' ? -2 : 2;
  }
 else   if (key.endsWith("%")) {
    keyType=3;
  }
 else   if (key.endsWith("{}")) {
    keyType=4;
  }
 else   if (key.endsWith("}{")) {
    keyType=5;
  }
 else   if (key.endsWith("<>")) {
    keyType=6;
  }
 else   if (key.endsWith(">=")) {
    keyType=7;
  }
 else   if (key.endsWith("<=")) {
    keyType=8;
  }
 else   if (key.endsWith(">")) {
    keyType=9;
  }
 else   if (key.endsWith("<")) {
    keyType=10;
  }
 else {
    keyType=0;
  }
  key=getRealKey(method,key,false,true,verifyName,getQuote());
switch (keyType) {
case 1:
    return getSearchString(key,value);
case -2:
case 2:
  return getRegExpString(key,value,keyType < 0);
case 3:
return getBetweenString(key,value);
case 4:
return getRangeString(key,value);
case 5:
return getExistsString(key,value);
case 6:
return getContainString(key,value);
case 7:
return getCompareString(key,value,">=");
case 8:
return getCompareString(key,value,"<=");
case 9:
return getCompareString(key,value,">");
case 10:
return getCompareString(key,value,"<");
default :
return getEqualString(key,value);
}
}

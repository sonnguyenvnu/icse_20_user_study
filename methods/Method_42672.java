public static boolean notifySign(Map<String,String> result,String sign,String partnerKey){
  String argNotifySign=getStringByStringMap(result) + "&key=" + partnerKey;
  String notifySign=MD5Util.encode(argNotifySign).toUpperCase();
  if (notifySign.equals(sign)) {
    return true;
  }
 else {
    return false;
  }
}

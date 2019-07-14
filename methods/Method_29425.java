public static String genSecretKey(long appId){
  StringBuilder key=new StringBuilder();
  key.append(appId).append(ConstUtils.APP_SECRET_BASE_KEY);
  char[] strs=key.toString().toCharArray();
  Arrays.sort(strs);
  return MD5(new String(strs));
}

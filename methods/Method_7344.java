private static Pattern makeNonEmptyRegex(String configKey){
  String r=VoIPServerConfig.getString(configKey,"");
  if (TextUtils.isEmpty(r))   return null;
  try {
    return Pattern.compile(r);
  }
 catch (  Exception x) {
    VLog.e(x);
    return null;
  }
}

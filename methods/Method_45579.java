/** 
 * ????????
 */
private static void initContext(){
  putIfAbsent(KEY_APPID,RpcConfigs.getOrDefaultValue(APP_ID,null));
  putIfAbsent(KEY_APPNAME,RpcConfigs.getOrDefaultValue(APP_NAME,null));
  putIfAbsent(KEY_APPINSID,RpcConfigs.getOrDefaultValue(INSTANCE_ID,null));
  putIfAbsent(KEY_APPAPTH,System.getProperty("user.dir"));
}

public static SofaRpcRuntimeException buildRuntime(String configKey,String configValue){
  String msg="The value of config " + configKey + " [" + configValue + "] is illegal, please check it";
  return new SofaRpcRuntimeException(msg);
}

public static SofaRpcRuntimeException buildRuntime(String configKey,String configValue,String message){
  String msg="The value of config " + configKey + " [" + configValue + "] is illegal, " + message;
  return new SofaRpcRuntimeException(msg);
}

private static void init(){
  try {
    String json=FileUtils.file2String(RpcConfigs.class,"rpc-config-default.json","UTF-8");
    Map map=JSON.parseObject(json,Map.class);
    CFG.putAll(map);
    loadCustom("sofa-rpc/rpc-config.json");
    loadCustom("META-INF/sofa-rpc/rpc-config.json");
    CFG.putAll(new HashMap(System.getProperties()));
  }
 catch (  Exception e) {
    throw new SofaRpcRuntimeException("Catch Exception when load RpcConfigs",e);
  }
}

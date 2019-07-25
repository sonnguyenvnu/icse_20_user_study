private static Config from(DynamicConfig config){
  Map<String,String> map=config.asMap();
  final Config hbaseConfig=new Config();
  for (  Map.Entry<String,String> entry : map.entrySet()) {
    hbaseConfig.overrideConfig(entry.getKey(),entry.getValue());
  }
  return hbaseConfig;
}

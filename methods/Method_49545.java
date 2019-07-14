private static void copyPropertiesToInputAndOutputConf(Configuration sink,Properties source){
  final String prefix=ConfigElement.getPath(JanusGraphHadoopConfiguration.GRAPH_CONFIG_KEYS,true) + ".";
  for (  Map.Entry<Object,Object> e : source.entrySet()) {
    String k;
    String v=e.getValue().toString();
    k=prefix + e.getKey().toString();
    sink.set(k,v);
    log.info("Set {}={}",k,v);
  }
}

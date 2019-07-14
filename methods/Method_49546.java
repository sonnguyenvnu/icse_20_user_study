private static void copyInputKeys(org.apache.hadoop.conf.Configuration hadoopConf,org.apache.commons.configuration.Configuration source){
  Iterator<String> keyIter=source.getKeys();
  while (keyIter.hasNext()) {
    String key=keyIter.next();
    String k=ConfigElement.getPath(JanusGraphHadoopConfiguration.GRAPH_CONFIG_KEYS,true) + "." + key;
    Object vObject=source.getProperty(key);
    String v;
    if (vObject instanceof Collection) {
      v=Joiner.on(",").join((Collection<String>)vObject);
    }
 else {
      v=vObject.toString();
    }
    hadoopConf.set(k,v);
    log.debug("[inputkeys] Set {}={}",k,v);
  }
}

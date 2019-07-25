@Modified void modified(Map<String,Object> config){
  if (config != null) {
    this.defaultSource=config.containsKey(CONFIG_DEFAULT_SOURCE) ? config.get(CONFIG_DEFAULT_SOURCE).toString() : null;
    this.defaultSink=config.containsKey(CONFIG_DEFAULT_SINK) ? config.get(CONFIG_DEFAULT_SINK).toString() : null;
  }
}

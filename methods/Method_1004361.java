public void update(final String configName,final Map<String,String> config){
  for (  Map.Entry<String,String> entry : config.entrySet()) {
    setString(entry.getKey(),entry.getValue());
    LOGGER.info("set pull config: {}. {}={}",configName,entry.getKey(),entry.getValue());
  }
  for (  String key : configMap.keySet()) {
    if (!config.containsKey(key)) {
      AtomicReference<T> valueRef=configMap.get(key);
      if (valueRef != null) {
        T oldValue=valueRef.get();
        updateValueOnNoConfiged(key,valueRef);
        T newValue=valueRef.get();
        LOGGER.info("update no pull config: {}. key={}, oldValue={}, newValue={}",configName,key,oldValue,newValue);
      }
    }
  }
}

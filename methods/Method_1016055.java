@Override public boolean has(ConfigurationKey key){
  return System.getProperty(key.getKey(variableValues),null) != null;
}

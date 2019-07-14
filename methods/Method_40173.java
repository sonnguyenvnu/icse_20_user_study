public boolean hasOption(String key){
  Object v=optionsMap.get(key);
  if (v instanceof Boolean) {
    return (boolean)v;
  }
 else {
    return false;
  }
}

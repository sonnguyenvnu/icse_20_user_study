public float getFloat(String key,float defaultValue){
  try {
    return getFloat(key);
  }
 catch (  Exception e) {
    return defaultValue;
  }
}

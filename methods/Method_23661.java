public float getFloat(int index,float defaultValue){
  try {
    return getFloat(index);
  }
 catch (  Exception e) {
    return defaultValue;
  }
}

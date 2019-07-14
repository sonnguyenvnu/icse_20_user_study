public void addDebugData(String key,String value){
  mDebugData.put(key,value);
  mMaxLineLength=Math.max(value.length(),mMaxLineLength);
}

@Override public void addDebugInfo(String key,Object value){
  if (mDebugInfo == null) {
    mDebugInfo=Collections.synchronizedMap(new HashMap<String,Object>());
  }
  mDebugInfo.put(key,value);
}

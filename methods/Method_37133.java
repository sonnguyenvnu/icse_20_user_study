public void setSpecialInterval(JSONObject jsonObject){
  if (jsonObject != null) {
    this.mSpecialInterval=new SparseIntArray();
    for (    String key : jsonObject.keySet()) {
      try {
        int index=Integer.parseInt(key);
        int value=jsonObject.getIntValue(key);
        if (value > 0) {
          this.mSpecialInterval.put(index,value);
        }
      }
 catch (      Exception e) {
      }
    }
  }
}

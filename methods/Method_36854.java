public void setSpecialInterval(JSONObject jsonObject){
  if (jsonObject != null) {
    this.mSpecialInterval=new SparseIntArray();
    Iterator<String> itr=jsonObject.keys();
    while (itr.hasNext()) {
      String key=itr.next();
      try {
        int index=Integer.parseInt(key);
        int value=jsonObject.optInt(key);
        if (value > 0) {
          this.mSpecialInterval.put(index,value);
        }
      }
 catch (      NumberFormatException e) {
      }
    }
  }
}

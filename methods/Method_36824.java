@Override public void parseStyle(JSONObject data){
  style=new DelegateStyle();
  if (data != null) {
    style.parseWith(data);
    for (    DelegateStyle.CardInfo info : ((DelegateStyle)style).cardInfos) {
      try {
        info.data.put("load",load);
        info.data.put("loadMore",loadMore);
        info.data.put("hasMore",hasMore);
      }
 catch (      JSONException e) {
        e.printStackTrace();
      }
    }
  }
}

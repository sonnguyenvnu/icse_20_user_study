@Override public void parseStyle(JSONObject data){
  this.style=new StickyStyle(true);
  if (data != null)   style.parseWith(data);
}

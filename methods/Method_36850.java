@Override public void parseStyle(JSONObject data){
  this.style=new StickyStyle(false);
  if (data != null)   style.parseWith(data);
}

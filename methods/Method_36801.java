private void parseBizParams(BaseCell cell,JSONObject json){
  if (json == null) {
    return;
  }
  Iterator<String> iterator=json.keys();
  while (iterator.hasNext()) {
    String key=iterator.next();
    cell.addBizParam(key,json.opt(key));
  }
}

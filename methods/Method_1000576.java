@Override public void render(Object obj) throws IOException {
  if (null == obj) {
    appendNull();
    return;
  }
  Mirror mirror=Mirror.me(obj);
  for (  JsonTypeHandler handler : Json.getTypeHandlers()) {
    if (handler.supportToJson(mirror,obj,format)) {
      if (handler.shallCheckMemo()) {
        if (memo.contains(obj)) {
          writer.write("null");
          return;
        }
        memo.add(obj);
        handler.toJson(mirror,obj,this,format);
        memo.remove(obj);
      }
 else       handler.toJson(mirror,obj,this,format);
      return;
    }
  }
  this.string2Json(String.valueOf(obj));
}

public void update(Map<?,?> map){
  for (  Map.Entry<?,?> en : map.entrySet()) {
    String key=en.getKey().toString();
    Object val=en.getValue();
    if (null == val)     continue;
    if ("statusCode".equals(key)) {
      this.statusCode=Castors.me().castTo(val,Integer.class);
      this.statusText=Http.getStatusText(statusCode);
    }
 else     if ("statusText".equals(key)) {
      this.statusText=val.toString();
    }
 else     if ("body".equals(key)) {
      try {
        body=val.toString().getBytes(Encoding.UTF8);
      }
 catch (      UnsupportedEncodingException e) {
        throw Lang.wrapThrow(e);
      }
    }
 else {
      this.header.put(key.toUpperCase(),val.toString());
    }
  }
}

@Override public SingleDictParser build(String config){
  Objects.requireNonNull(config,"config is null");
  JSONObject object=JSON.parseObject(config);
  String type=object.getString("type");
switch (type) {
case "simple":
    return object.getObject("parser",SimpleSingleDictParser.class);
case "script":
default :
  throw new UnsupportedOperationException(config);
}
}

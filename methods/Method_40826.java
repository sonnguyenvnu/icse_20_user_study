public static Map<String,Node> parseMap(List<Node> prenodes){
  Map<String,Node> ret=new LinkedHashMap<>();
  if (prenodes.size() % 2 != 0) {
    _.abort("must be of the form (:key1 value1 :key2 value2), but got: " + prenodes);
    return null;
  }
  for (int i=0; i < prenodes.size(); i+=2) {
    Node key=prenodes.get(i);
    Node value=prenodes.get(i + 1);
    if (!(key instanceof Keyword)) {
      _.abort(key,"key must be a keyword, but got: " + key);
    }
    ret.put(((Keyword)key).id,value);
  }
  return ret;
}

public void parse(Map<String,Object> params){
  for (  Map.Entry<String,Object> entry : params.entrySet()) {
    String[] keys=entry.getKey().split(keyPartDelimiter);
    if (keys.length > 2)     throw new ArgumentErrorException("??????????????");
    if (keys.length == 1) {
      rootValues.put(keys[0],entry.getValue());
    }
 else {
      if (_children.get(keys[0]) == null) {
        _children.put(keys[0],map(keys[1],entry.getValue()));
      }
 else {
        _children.get(keys[0]).put(keys[1],entry.getValue());
        _children.put(keys[0],_children.get(keys[0]));
      }
    }
  }
}

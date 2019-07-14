private static JsonNode getNode(JsonNode ret,List<String> path,int pos){
  if (pos >= path.size()) {
    return ret;
  }
  if (ret == null) {
    return null;
  }
  String key=path.get(pos);
  if (ret.isArray()) {
    int keyInt=Integer.parseInt(key.replaceAll("\"",""));
    return getNode(ret.get(keyInt),path,++pos);
  }
 else   if (ret.isObject()) {
    if (ret.has(key)) {
      return getNode(ret.get(key),path,++pos);
    }
    return null;
  }
 else {
    return ret;
  }
}

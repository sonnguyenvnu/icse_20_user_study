public static Map<String,String> headers(String token){
  JwtImpl jwt=(JwtImpl)decode(token);
  Map<String,String> map=new LinkedHashMap<String,String>(jwt.header.parameters.map);
  map.put("alg",jwt.header.parameters.alg);
  if (jwt.header.parameters.typ != null) {
    map.put("typ",jwt.header.parameters.typ);
  }
  return map;
}

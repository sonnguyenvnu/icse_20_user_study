private static Map<String,Object> readJson(HttpServletRequest request){
  try {
    String json=CharStreams.toString(request.getReader());
    return jsonToMap(json);
  }
 catch (  IOException e) {
    throw new RuntimeException(e);
  }
}

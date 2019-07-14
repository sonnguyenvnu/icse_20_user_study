private String fixJsonFromElastic(String elasticJson){
  String properJson=elasticJson.replaceAll("=",":");
  properJson=properJson.replaceAll("(type)(:)([a-zA-Z]+)","\"type\":\"$3\"");
  properJson=properJson.replaceAll("coordinates","\"coordinates\"");
  return properJson;
}

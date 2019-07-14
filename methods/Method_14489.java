@JsonProperty("constraints") public void setConstraintsJson(String json){
  try {
    setConstraints(ParsingUtilities.mapper.readValue(json,new TypeReference<Map<String,Object>>(){
    }
));
  }
 catch (  IOException e) {
    e.printStackTrace();
  }
}

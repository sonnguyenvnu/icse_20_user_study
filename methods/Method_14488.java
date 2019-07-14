@JsonProperty("constraints") public String getConstraintsString(){
  try {
    return ParsingUtilities.mapper.writeValueAsString(constraints);
  }
 catch (  JsonProcessingException e) {
    e.printStackTrace();
    return "{}";
  }
}

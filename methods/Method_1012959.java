private Map<String,String> details(Check check,List<Alert> alerts) throws JsonProcessingException {
  ObjectMapper mapper=new ObjectMapper();
  mapper.configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS,false);
  mapper.configure(DeserializationFeature.FAIL_ON_IGNORED_PROPERTIES,false);
  mapper.setPropertyNamingStrategy(new PropertyNamingStrategy.LowerCaseWithUnderscoresStrategy());
  return ImmutableMap.<String,String>builder().put("CHECK",mapper.writeValueAsString(check)).put("STATE",check.getState().name()).put("ALERTS",mapper.writeValueAsString(alerts)).put("SEYREN_URL",seyrenConfig.getBaseUrl()).build();
}

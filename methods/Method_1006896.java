public Map<String,Object> deserialize(InputStream in) throws IOException {
  TypeReference<HashMap<String,Object>> typeRef=new TypeReference<HashMap<String,Object>>(){
  }
;
  return objectMapper.readValue(in,typeRef);
}

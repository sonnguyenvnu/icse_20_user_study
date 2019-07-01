public static <K>String _XXXXX_(K spec){
  ObjectMapper mapper=new ObjectMapper();
  try {
    String json=mapper.writeValueAsString(spec);
    return json;
  }
 catch (  Exception ex) {
    LOG.error("error in serializing object {} with type {}",spec,new TypeReference<K>(){
    }
.getType().getClass().getCanonicalName(),ex);
  }
  return null;
}
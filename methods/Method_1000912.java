public static JsonMappingException from(SerializerProvider ctxt,String msg){
  return new JsonMappingException(ctxt.getGenerator(),msg);
}

public static InvalidTypeIdException from(JsonParser p,String msg,JavaType baseType,String typeId){
  return new InvalidTypeIdException(p,msg,baseType,typeId);
}

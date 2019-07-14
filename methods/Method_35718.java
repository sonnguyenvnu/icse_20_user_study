public static byte[] toByteArray(Object object){
  try {
    ObjectMapper mapper=getObjectMapper();
    return mapper.writeValueAsBytes(object);
  }
 catch (  IOException ioe) {
    return throwUnchecked(ioe,byte[].class);
  }
}

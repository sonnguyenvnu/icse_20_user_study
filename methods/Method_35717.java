public static <T>String write(T object,Class<?> view){
  try {
    ObjectMapper mapper=getObjectMapper();
    ObjectWriter objectWriter=mapper.writerWithDefaultPrettyPrinter();
    if (view != null) {
      objectWriter=objectWriter.withView(view);
    }
    return objectWriter.writeValueAsString(object);
  }
 catch (  IOException ioe) {
    return throwUnchecked(ioe,String.class);
  }
}

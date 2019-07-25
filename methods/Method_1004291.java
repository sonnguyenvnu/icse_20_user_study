public static StatusLine get(Response response){
  return new StatusLine(response.protocol(),response.code(),response.message());
}

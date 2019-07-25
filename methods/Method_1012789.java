public static void respond(HttpExchange t,String response,int status,String mime){
  if (response != null) {
    respond(t,response.getBytes(),status,mime);
  }
}

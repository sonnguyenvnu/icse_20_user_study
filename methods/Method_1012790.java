public static void respond(HttpExchange t,byte[] response,int status,String mime){
  if (response != null) {
    if (mime != null) {
      Headers hdr=t.getResponseHeaders();
      hdr.add("Content-Type",mime);
    }
    try (OutputStream os=t.getResponseBody()){
      t.sendResponseHeaders(status,response.length);
      os.write(response);
      os.close();
    }
 catch (    Exception e) {
      LOGGER.debug("Error sending response: " + e);
    }
  }
}

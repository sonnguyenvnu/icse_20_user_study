@Override public void doGet(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException {
  try {
    response.setCharacterEncoding("UTF-8");
    response.setHeader("Content-Type","application/json");
    String sessionToken=TokenCookie.getToken(request);
    if (sessionToken != null) {
      HttpRequestFactory factory=HTTP_TRANSPORT.createRequestFactory();
      GenericUrl url=new GenericUrl("https://accounts.google.com/o/oauth2/revoke?token=" + sessionToken);
      HttpRequest rqst=factory.buildGetRequest(url);
      HttpResponse resp=rqst.execute();
      if (resp.getStatusCode() != 200) {
        respond(response,String.valueOf(resp.getStatusCode()),resp.getStatusMessage());
      }
      TokenCookie.deleteToken(request,response);
    }
    respond(response,"200 OK","");
  }
 catch (  Exception e) {
    respondException(response,e);
  }
}

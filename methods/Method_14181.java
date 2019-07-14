@Override public void doGet(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException {
  try {
    response.setCharacterEncoding("UTF-8");
    response.setHeader("Content-Type","application/json");
    Provider provider=OAuthUtilities.getProvider(request);
    Credentials.deleteCredentials(request,response,provider,Credentials.Type.ACCESS);
    respond(response,"200 OK","");
  }
 catch (  Exception e) {
    respondException(response,e);
  }
}

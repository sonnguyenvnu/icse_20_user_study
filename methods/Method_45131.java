@Override public void onCompleteResponse(final HttpRequest request,final HttpResponse response){
  try {
    ObjectWriter writer=mapper.writerWithDefaultPrettyPrinter();
    Session targetSession=Session.newSession(request,response);
    writer.writeValue(this.file,prepareTargetSessions(this.file,targetSession));
  }
 catch (  IOException e) {
    throw new MocoException(e);
  }
}

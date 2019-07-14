private ImmutableList<Session> restoreSessions(final File file){
  try {
    InputStream inputStream=new FileInputStream(file);
    return Jsons.toObjects(inputStream,Session.class);
  }
 catch (  MocoException me) {
    logger.error("exception found",me);
    return of();
  }
catch (  IOException e) {
    throw new MocoException(e);
  }
}

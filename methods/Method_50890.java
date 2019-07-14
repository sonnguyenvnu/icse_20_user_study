private static void addSourceURIToCPD(String uri,CPD cpd){
  try {
    LOGGER.fine(String.format("Attempting DBURI=%s",uri));
    DBURI dburi=new DBURI(uri);
    LOGGER.fine(String.format("Initialised DBURI=%s",dburi));
    LOGGER.fine(String.format("Adding DBURI=%s with DBType=%s",dburi.toString(),dburi.getDbType().toString()));
    cpd.add(dburi);
  }
 catch (  IOException|URISyntaxException e) {
    throw new IllegalStateException("uri=" + uri,e);
  }
}

public GHashIOHashrate getHashrate() throws IOException {
  return cexIOAuthenticated.getHashrate(signatureCreator);
}

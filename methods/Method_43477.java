public synchronized ParamsDigest getSignatureCreator() throws IOException {
  if (secretData == null || secretExpiresTime - System.currentTimeMillis() < 60 * 1000) {
    refresh();
  }
  return signatureCreator;
}

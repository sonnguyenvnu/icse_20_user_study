public KuduRowIterator scanner(byte[] token) throws IOException {
  return new KuduRowIterator(KuduScanToken.deserializeIntoScanner(token,client.syncClient()));
}

private void extractSignature(String sig){
  if (sig != null) {
    new SignatureReader(sig).accept(sigVisitor);
  }
}

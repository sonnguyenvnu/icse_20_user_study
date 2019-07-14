@Override public boolean hasMatchingOpSig(String name,JavaOperationSigMask mask){
  for (  Entry<JavaOperationSignature,Set<String>> entry : operations.entrySet()) {
    if (mask.covers(entry.getKey())) {
      if (entry.getValue().contains(name)) {
        return true;
      }
    }
  }
  return false;
}

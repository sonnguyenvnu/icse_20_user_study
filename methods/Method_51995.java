@Override public boolean hasMatchingFieldSig(String name,JavaFieldSigMask mask){
  for (  Entry<JavaFieldSignature,Set<String>> entry : fields.entrySet()) {
    if (mask.covers(entry.getKey())) {
      if (entry.getValue().contains(name)) {
        return true;
      }
    }
  }
  return false;
}

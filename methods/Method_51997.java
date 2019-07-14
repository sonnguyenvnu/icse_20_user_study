@Override public int countMatchingFieldSigs(JavaFieldSigMask sigMask){
  int sum=0;
  for (  Entry<JavaFieldSignature,Set<String>> e : fields.entrySet()) {
    if (sigMask.covers(e.getKey())) {
      sum+=e.getValue().size();
    }
  }
  return sum;
}

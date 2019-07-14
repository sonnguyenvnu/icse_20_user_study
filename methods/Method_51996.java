@Override public int countMatchingOpSigs(JavaOperationSigMask sigMask){
  int sum=0;
  for (  Entry<JavaOperationSignature,Set<String>> e : operations.entrySet()) {
    if (sigMask.covers(e.getKey())) {
      sum+=e.getValue().size();
    }
  }
  return sum;
}

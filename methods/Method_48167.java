@Override public void logMutations(DataOutput out){
  storeTx.logMutations(out);
  for (  Map.Entry<String,IndexTransaction> itx : indexTx.entrySet()) {
    out.writeObjectNotNull(itx.getKey());
    itx.getValue().logMutations(out);
  }
}

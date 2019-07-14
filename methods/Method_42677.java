@Override public String getSeqNextValue(SeqBuild seqBuild){
  return super.getSessionTemplate().selectOne(getStatement("getSeqNextValue"),seqBuild);
}

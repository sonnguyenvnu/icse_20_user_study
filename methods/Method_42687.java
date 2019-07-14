/** 
 * ??????,?????
 */
@Transactional(rollbackFor=Exception.class) public String getSeqNextValue(String seqName){
  String seqNextValue=null;
  try {
    SeqBuild seqBuild=new SeqBuild();
    seqBuild.setSeqName(seqName);
    seqNextValue=buildNoDao.getSeqNextValue(seqBuild);
  }
 catch (  Exception e) {
    LOG.error("???????" + "seqName=" + seqName,e);
    throw BizException.DB_GET_SEQ_NEXT_VALUE_ERROR;
  }
  if (StringUtils.isEmpty(seqNextValue)) {
    throw BizException.DB_GET_SEQ_NEXT_VALUE_ERROR;
  }
  return seqNextValue;
}

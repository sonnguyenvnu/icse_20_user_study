public boolean delete(long groupIdHash,long unitIdHash){
  String sql="DELETE FROM TXLCN_LOG WHERE GROUP_ID_HASH = ? and UNIT_ID_HASH = ?";
  return h2DbHelper.update(sql,groupIdHash,unitIdHash) > 0;
}

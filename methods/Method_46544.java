public boolean delete(String groupId){
  String sql="DELETE FROM TXLCN_LOG WHERE GROUP_ID = ?";
  return h2DbHelper.update(sql,groupId) > 0;
}

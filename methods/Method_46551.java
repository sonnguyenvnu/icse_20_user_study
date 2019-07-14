/** 
 * ??TXC????
 * @param groupId groupId
 * @param unitId  unitId
 * @throws SQLException ???????
 */
public void deleteUndoLog(String groupId,String unitId) throws SQLException {
  String sql="DELETE FROM TXC_UNDO_LOG WHERE GROUP_ID=? AND UNIT_ID=?";
  h2DbHelper.queryRunner().update(sql,groupId,unitId);
}

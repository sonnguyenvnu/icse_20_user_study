/** 
 * ?????????UNIT?TXC????
 * @param groupId groupId
 * @param unitId  unitId
 * @return undo log list
 * @throws SQLException ???????
 */
public List<UndoLogDO> getUndoLogByGroupAndUnitId(String groupId,String unitId) throws SQLException {
  String sql="SELECT * FROM TXC_UNDO_LOG WHERE GROUP_ID = ? and UNIT_ID = ?";
  return h2DbHelper.queryRunner().query(sql,rs -> {
    List<UndoLogDO> undoLogDOList=new ArrayList<>();
    while (rs.next()) {
      UndoLogDO undoLogDO=new UndoLogDO();
      undoLogDO.setSqlType(rs.getInt("SQL_TYPE"));
      undoLogDO.setRollbackInfo(rs.getBytes("ROLLBACK_INFO"));
      undoLogDO.setUnitId(rs.getString("UNIT_ID"));
      undoLogDO.setGroupId("GROUP_ID");
      undoLogDO.setCreateTime(rs.getString("CREATE_TIME"));
      undoLogDOList.add(undoLogDO);
    }
    return undoLogDOList;
  }
,groupId,unitId);
}

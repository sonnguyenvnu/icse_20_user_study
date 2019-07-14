/** 
 * ??????
 * @param rs      rs
 * @param columns columns
 * @return ModifiedRecord
 * @throws SQLException SQLException
 */
public static ModifiedRecord recordByColumns(ResultSet rs,List<String> columns) throws SQLException {
  ModifiedRecord record=new ModifiedRecord();
  for (  String column : columns) {
    FieldValue fieldValue=new FieldValue();
    fieldValue.setFieldName(column);
    fieldValue.setTableName(SqlUtils.tableName(column));
    fieldValue.setValue(rs.getObject(column));
    fieldValue.setValueType(Objects.isNull(fieldValue.getValue()) ? Void.class : fieldValue.getValue().getClass());
    if (record.getFieldClusters().get(fieldValue.getTableName()) != null) {
      record.getFieldClusters().get(fieldValue.getTableName()).getFields().add(fieldValue);
    }
 else {
      FieldCluster fieldCluster=new FieldCluster();
      fieldCluster.getFields().add(fieldValue);
      record.getFieldClusters().put(fieldValue.getTableName(),fieldCluster);
    }
  }
  return record;
}

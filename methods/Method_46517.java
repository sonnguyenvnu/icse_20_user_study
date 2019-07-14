private static int whereBuilder(List<FieldValue> primaryKeys,StringBuilder sqlBuilder,Object[] params,int index){
  int j=index;
  for (  FieldValue fieldValue : primaryKeys) {
    j++;
    if (Objects.isNull(fieldValue.getValue())) {
      j--;
      continue;
    }
    sqlBuilder.append(fieldValue.getFieldName()).append("=?").append(SqlUtils.AND);
    params[j]=fieldValue.getValue();
  }
  return j + 1;
}

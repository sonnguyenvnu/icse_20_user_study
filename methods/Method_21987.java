private void setBindValue(final PreparedStatement preparedStatement,final Collection<String> tableFields,final Condition condition) throws SQLException {
  int index=1;
  if (null != condition.getFields() && !condition.getFields().isEmpty()) {
    for (    Map.Entry<String,Object> entry : condition.getFields().entrySet()) {
      String lowerUnderscore=CaseFormat.LOWER_CAMEL.to(CaseFormat.LOWER_UNDERSCORE,entry.getKey());
      if (null != entry.getValue() && tableFields.contains(lowerUnderscore)) {
        preparedStatement.setString(index++,String.valueOf(entry.getValue()));
      }
    }
  }
  if (null != condition.getStartTime()) {
    preparedStatement.setTimestamp(index++,new Timestamp(condition.getStartTime().getTime()));
  }
  if (null != condition.getEndTime()) {
    preparedStatement.setTimestamp(index,new Timestamp(condition.getEndTime().getTime()));
  }
}

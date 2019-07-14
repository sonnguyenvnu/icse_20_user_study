private List<String> whereSqlAppender(StringBuilder sql,List<Field> fields){
  List<String> values=new ArrayList<>(fields.size());
  fields.forEach(field -> {
    if (field instanceof GroupId) {
      sql.append("group_id=? and ");
      values.add(((GroupId)field).getGroupId());
    }
 else     if (field instanceof Tag) {
      sql.append("tag=? and ");
      values.add(((Tag)field).getTag());
    }
 else     if (field instanceof StartTime) {
      sql.append("create_time > ? and ");
      values.add(((StartTime)field).getStartTime());
    }
 else     if (field instanceof StopTime) {
      sql.append("create_time < ? and ");
      values.add(((StopTime)field).getStopTime());
    }
  }
);
  sql.delete(sql.length() - 4,sql.length());
  return values;
}

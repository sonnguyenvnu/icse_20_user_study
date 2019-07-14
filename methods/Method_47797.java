@Override public Long insert(String tableName,Map<String,Object> map){
  try {
    ArrayList<String> fields=new ArrayList<>();
    ArrayList<Object> params=new ArrayList<>();
    ArrayList<String> questionMarks=new ArrayList<>();
    for (    Map.Entry<String,Object> entry : map.entrySet()) {
      if (entry.getValue() == null)       continue;
      fields.add(entry.getKey());
      params.add(entry.getValue());
      questionMarks.add("?");
    }
    String query=String.format("insert into %s(%s) values(%s)",tableName,StringUtils.join(fields,", "),StringUtils.join(questionMarks,", "));
    PreparedStatement st=buildStatement(query,params.toArray());
    st.execute();
    Long id=null;
    ResultSet keys=st.getGeneratedKeys();
    if (keys.next())     id=keys.getLong(1);
    return id;
  }
 catch (  SQLException e) {
    throw new RuntimeException(e);
  }
}

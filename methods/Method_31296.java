@Override protected Function[] doAllFunctions() throws SQLException {
  List<String> functionNames=jdbcTemplate.queryForStringList("select SPECIFICNAME from SYSCAT.ROUTINES where" + " ROUTINETYPE='F'" + " AND ORIGIN IN (" + "'E', " + "'M', " + "'Q', " + "'U')" + " and ROUTINESCHEMA = ?",name);
  List<Function> functions=new ArrayList<>();
  for (  String functionName : functionNames) {
    functions.add(getFunction(functionName));
  }
  return functions.toArray(new Function[0]);
}

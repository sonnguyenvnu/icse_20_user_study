private PreparedStatement buildStatement(String query,Object[] params) throws SQLException {
  PreparedStatement st=connection.prepareStatement(query);
  int index=1;
  for (  Object param : params)   st.setString(index++,param.toString());
  return st;
}

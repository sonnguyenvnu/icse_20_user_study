/** 
 * return source code text
 * @param objectType
 * @param name Source Code name
 * @param schema Owner of the code
 * @return Source code text.
 * @throws SQLException on failing to retrieve the source Code text
 */
public java.io.Reader getSourceCode(String objectType,String name,String schema) throws SQLException {
  Object result;
  if (null == callableStatement) {
    if (LOGGER.isLoggable(Level.FINEST)) {
      LOGGER.finest("getSourceCode: returnSourceCodeStatement=\"" + returnSourceCodeStatement + "\"");
      LOGGER.finest("getSourceCode: returnType=\"" + returnType + "\"");
    }
    callableStatement=getConnection().prepareCall(returnSourceCodeStatement);
    callableStatement.registerOutParameter(1,returnType);
  }
  callableStatement.setString(2,objectType);
  callableStatement.setString(3,name);
  callableStatement.setString(4,schema);
  callableStatement.executeUpdate();
  result=callableStatement.getObject(1);
  return (java.sql.Types.CLOB == returnType) ? ((Clob)result).getCharacterStream() : new java.io.StringReader(result.toString());
}

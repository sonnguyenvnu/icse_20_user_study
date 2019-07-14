/** 
 * Generates the query for the prepared statement with all parameter placeholders replaced with the actual parameter values
 * @return the SQL
 */
@Override public String getSqlWithValues(){
  final StringBuilder sb=new StringBuilder();
  final String statementQuery=getStatementQuery();
  int currentParameter=0;
  for (int pos=0; pos < statementQuery.length(); pos++) {
    char character=statementQuery.charAt(pos);
    if (statementQuery.charAt(pos) == '?' && currentParameter <= parameterValues.size()) {
      Value value=parameterValues.get(currentParameter);
      sb.append(value != null ? value.toString() : new Value().toString());
      currentParameter++;
    }
 else {
      sb.append(character);
    }
  }
  return sb.toString();
}

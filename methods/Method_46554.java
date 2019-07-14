/** 
 * Generates the query for the callable statement with all parameter placeholders replaced with the actual parameter values
 * @return the SQL
 */
@Override public String getSqlWithValues(){
  if (namedParameterValues.size() == 0) {
    return super.getSqlWithValues();
  }
  final StringBuilder result=new StringBuilder();
  final String statementQuery=getStatementQuery();
  result.append(statementQuery);
  result.append(" ");
  StringBuilder parameters=new StringBuilder();
  for (  Map.Entry<Integer,Value> entry : getParameterValues().entrySet()) {
    appendParameter(parameters,entry.getKey().toString(),entry.getValue());
  }
  for (  Map.Entry<String,Value> entry : namedParameterValues.entrySet()) {
    appendParameter(parameters,entry.getKey(),entry.getValue());
  }
  result.append(parameters);
  return result.toString();
}

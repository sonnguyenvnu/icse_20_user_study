/** 
 * Returns single value of a parameter. If parameter name is used for more then one parameter, only the first one will be returned.
 * @return parameter value, or <code>null</code> if not found
 */
public String getParameter(final String paramName){
  if (requestParameters == null) {
    return null;
  }
  String[] values=requestParameters.get(paramName);
  if ((values != null) && (values.length > 0)) {
    return values[0];
  }
  return null;
}

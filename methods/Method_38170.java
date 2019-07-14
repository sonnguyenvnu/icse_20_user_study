/** 
 * Saves the parameter value <code>obj</code> for the specified <code>position</code> for use in logging output.
 * @param position position (starting at 1) of the parameter to save
 * @param obj      java.lang.Object the parameter value to save
 */
protected void saveQueryParamValue(final int position,final Object obj){
  final String strValue;
  if (obj instanceof String || obj instanceof Date) {
    strValue="'" + obj + '\'';
  }
 else   if (obj instanceof LocalDateTime || obj instanceof LocalDate || obj instanceof LocalTime) {
    strValue="'" + Converter.get().toString(obj) + '\'';
  }
 else   if (obj == null) {
    strValue="<null>";
  }
 else {
    strValue=Converter.get().toString(obj);
  }
  if (parameterValues == null) {
    parameterValues=new ArrayList<>();
  }
  while (position >= parameterValues.size()) {
    parameterValues.add(null);
  }
  parameterValues.set(position,strValue);
}

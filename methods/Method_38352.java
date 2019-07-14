/** 
 * Adds query parameter.
 */
public void addParameter(final String name,final Object value,final DbEntityColumnDescriptor dec){
  if (parameters == null) {
    parameters=new HashMap<>();
  }
  parameters.put(name,new ParameterValue(value,dec));
}

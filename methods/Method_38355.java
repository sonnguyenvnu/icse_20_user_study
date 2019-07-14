/** 
 * Defines parameter with name and its value.
 */
protected void defineParameter(final StringBuilder query,String name,final Object value){
  if (name == null) {
    name=getNextParameterName();
  }
  query.append(':').append(name);
  addParameter(name,value,null);
}

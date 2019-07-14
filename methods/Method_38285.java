/** 
 * Defines parameter with name and its value.
 */
protected void defineParameter(final StringBuilder query,String name,final Object value,final DbEntityColumnDescriptor dec){
  if (name == null) {
    name=templateData.getNextParameterName();
  }
  query.append(':').append(name);
  templateData.addParameter(name,value,dec);
}

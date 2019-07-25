/** 
 * {@inheritDoc}
 * @param warnings
 * @return
 */
@Override public boolean validate(List<String> warnings){
  Properties properties=this.getProperties();
  this.tableSearchString=properties.getProperty(PRO_TABLE_SEARCH_STRING);
  this.tableReplaceString=properties.getProperty(PRO_TABLE_REPLACE_STRING);
  this.columnSearchString=properties.getProperty(PRO_COLUMN_SEARCH_STRING);
  this.columnReplaceString=properties.getProperty(PRO_COLUMN_REPLACE_STRING);
  this.exampleSuffix=properties.getProperty(PRO_EXAMPLE_SUFFIX);
  this.clientSuffix=properties.getProperty(PRO_CLIENT_SUFFIX);
  this.modelSuffix=properties.getProperty(PRO_MODEL_SUFFIX);
  return super.validate(warnings);
}

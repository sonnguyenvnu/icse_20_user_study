/** 
 * Internal direct header setting.
 */
protected void _headerRaw(String name,String value,final boolean overwrite){
  name=name.trim();
  value=value.trim();
  if (overwrite) {
    headers.setHeader(name,value);
  }
 else {
    headers.addHeader(name,value);
  }
}

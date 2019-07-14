/** 
 * Adds many header parameters at once.
 * @see #header(String,String)
 */
public T header(final Map<String,String> headerMap){
  for (  Map.Entry<String,String> entry : headerMap.entrySet()) {
    header(entry.getKey(),entry.getValue());
  }
  return _this();
}

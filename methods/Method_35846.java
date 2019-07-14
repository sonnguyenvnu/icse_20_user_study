/** 
 * @deprecated use requestLine to access information about the request
 */
@Deprecated public String getUrl(){
  return requestLine.getPath();
}

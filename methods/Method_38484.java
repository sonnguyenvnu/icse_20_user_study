/** 
 * Returns last response HTML page.
 */
public String getPage(){
  if (httpResponse == null) {
    return null;
  }
  return httpResponse.bodyText();
}

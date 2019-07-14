/** 
 * Returns encoded HTML text.
 */
public String getTextValue(){
  if (encodedText == null) {
    encodedText=HtmlEncoder.text(nodeValue);
  }
  return encodedText;
}

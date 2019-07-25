/** 
 * ??
 * @param textElement
 * @return
 */
public static TextElement clone(TextElement textElement){
  return new TextElement(textElement.getContent());
}

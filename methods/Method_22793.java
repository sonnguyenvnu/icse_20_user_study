/** 
 * Returns the length of the specified line.
 * @param line The line
 */
public int getLineLength(int line){
  Element lineElement=document.getDefaultRootElement().getElement(line);
  return (lineElement == null) ? -1 : lineElement.getEndOffset() - lineElement.getStartOffset() - 1;
}

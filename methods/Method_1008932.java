/** 
 * http://webapp.docx4java.org/OnlineDemo/ecma376/WordML/ST_TblOverlap.html
 * @param source
 * @param destination
 * @return
 */
public static STTblOverlap apply(STTblOverlap source,STTblOverlap destination){
  return (source == null ? destination : source);
}

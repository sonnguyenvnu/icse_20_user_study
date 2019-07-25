/** 
 * Tbl, Tc borders; PBdr, and RPr border. http://webapp.docx4java.org/OnlineDemo/ecma376/WordML/ST_Border.html
 * @param source
 * @param destination
 * @return
 */
private static STBorder apply(STBorder source,STBorder destination){
  return (source == null ? destination : source);
}

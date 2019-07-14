/** 
 * load a single svg icon from a file
 * @param url of the svg icon
 * @return SVGGLyph node
 * @throws IOException
 */
public static SVGGlyph loadGlyph(URL url) throws IOException {
  String urlString=url.toString();
  String filename=urlString.substring(urlString.lastIndexOf('/') + 1);
  int startPos=0;
  int endPos=0;
  while (endPos < filename.length() && filename.charAt(endPos) != '-') {
    endPos++;
  }
  int id=Integer.parseInt(filename.substring(startPos,endPos));
  startPos=endPos + 1;
  while (endPos < filename.length() && filename.charAt(endPos) != '.') {
    endPos++;
  }
  String name=filename.substring(startPos,endPos);
  return new SVGGlyph(id,name,extractSvgPath(getStringFromInputStream(url.openStream())),Color.BLACK);
}

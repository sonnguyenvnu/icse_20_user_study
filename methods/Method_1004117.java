/** 
 * Renders this page's content and optionally additional pages. This method must be called at most once.
 * @throws IOException if the page can't be written
 */
public void render() throws IOException {
  final HTMLElement html=new HTMLElement(folder.createFile(getFileName()),context.getOutputEncoding());
  html.attr("lang",context.getLocale().getLanguage());
  head(html.head());
  body(html.body());
  html.close();
}

/** 
 * Creates the elements within the head element.
 * @param head head tag of the page
 * @throws IOException in case of IO problems with the report writer
 */
protected void head(final HTMLElement head) throws IOException {
  head.meta("Content-Type","text/html;charset=UTF-8");
  head.link("stylesheet",context.getResources().getLink(folder,Resources.STYLESHEET),"text/css");
  head.link("shortcut icon",context.getResources().getLink(folder,"report.gif"),"image/gif");
  head.title().text(getLinkLabel());
}

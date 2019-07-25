/** 
 * This method is called when executing this application from the command line.
 * @param args the command line parameters
 */
public static void main(String... args) throws Exception {
  String[] pages={"quickstart.html","installation.html","tutorial.html","features.html","performance.html","advanced.html","commands.html","functions.html","functions-aggregate.html","functions-window.html","datatypes.html","grammar.html","systemtables.html","build.html","history.html","faq.html"};
  StringBuilder buff=new StringBuilder();
  for (  String fileName : pages) {
    String text=getContent(fileName);
    for (    String page : pages) {
      text=StringUtils.replaceAll(text,page + "#","#");
    }
    text=disableRailroads(text);
    text=removeHeaderFooter(fileName,text);
    text=fixLinks(text);
    text=fixTableBorders(text);
    buff.append(text);
  }
  String finalText=buff.toString();
  File output=new File(BASE_DIR,"onePage.html");
  PrintWriter writer=new PrintWriter(new FileWriter(output));
  writer.println("<html><head><meta http-equiv=\"Content-Type\" " + "content=\"text/html;charset=utf-8\" /><title>");
  writer.println("H2 Documentation");
  writer.println("</title><link rel=\"stylesheet\" type=\"text/css\" " + "href=\"stylesheetPdf.css\" /></head><body>");
  writer.println("<p class=\"title\">H2 Database Engine</p>");
  writer.println("<p>Version " + Constants.getFullVersion() + "</p>");
  writer.println(finalText);
  writer.println("</body></html>");
  writer.close();
}

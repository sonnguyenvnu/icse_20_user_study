/** 
 * This method is called when executing this application from the command line.
 * @param args the command line parameters
 */
public static void main(String... args) throws Exception {
  String baseDir="src/docsrc/textbase";
  prepare(baseDir,"src/main/org/h2/res",true);
  prepare(baseDir,"src/main/org/h2/server/web/res",true);
  PropertiesToUTF8.textUTF8ToProperties("src/docsrc/text/_docs_de.utf8.txt","src/docsrc/text/_docs_de.properties");
  PropertiesToUTF8.textUTF8ToProperties("src/docsrc/text/_docs_ja.utf8.txt","src/docsrc/text/_docs_ja.properties");
  extractFromHtml("docs/html","src/docsrc/text");
  prepare(baseDir,"src/docsrc/text",false);
  buildHtml("src/docsrc/text","docs/html","en");
  PropertiesToUTF8.propertiesToTextUTF8("src/docsrc/text/_docs_en.properties","src/docsrc/text/_docs_en.utf8.txt");
  PropertiesToUTF8.propertiesToTextUTF8("src/docsrc/text/_docs_de.properties","src/docsrc/text/_docs_de.utf8.txt");
  PropertiesToUTF8.propertiesToTextUTF8("src/docsrc/text/_docs_ja.properties","src/docsrc/text/_docs_ja.utf8.txt");
  for (  File f : new File("src/docsrc/text").listFiles()) {
    if (!f.getName().endsWith(".utf8.txt")) {
      f.delete();
    }
  }
}

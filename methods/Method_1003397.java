/** 
 * This method is called when executing this application from the command line.
 * @param args the command line parameters
 */
public static void main(String... args) throws IOException {
  String dir=Utils.getProperty("spellcheckDir","src");
  new SpellChecker().run("src/tools/org/h2/build/doc/dictionary.txt",dir);
}

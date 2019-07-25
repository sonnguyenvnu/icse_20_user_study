/** 
 * This method is called when executing this application from the command line.
 * @param args the command line parameters
 */
public static void main(String... args) throws Exception {
  convert("bin/org/h2/res");
  convert("bin/org/h2/server/web/res");
}

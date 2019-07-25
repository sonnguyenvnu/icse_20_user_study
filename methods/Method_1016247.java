/** 
 * Increments library build version and updates build date.
 * @param args arguments
 */
public static void main(final String[] args) throws IOException {
  VersionManager.initialize();
  if (args.length == 0 || args[0].equals("increment") || args[0].equals("decrement")) {
    updateVersion(args.length == 0 || args[0].equals("increment") ? 1 : -1);
  }
 else   if (args[0].equals("update")) {
    updateVersion(-1);
    updateVersion(+1);
  }
}

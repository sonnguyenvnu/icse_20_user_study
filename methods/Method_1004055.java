/** 
 * Called during the build process.
 * @param args exactly one argument expected with the target location
 * @throws IOException if XML document cannot be written
 */
public static void main(final String... args) throws IOException {
  final File file=new File(args[0]);
  file.getParentFile().mkdirs();
  final XMLElement root=new XMLElement("documentation",null,null,true,"UTF-8",new FileOutputStream(file));
  for (  final Command c : AllCommands.get()) {
    writeCommand(c,root);
  }
  root.close();
}

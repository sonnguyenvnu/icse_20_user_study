public static void main(String args[]) throws IOException, ReflectiveOperationException {
  ReflectiveConfigOptionLoader.INSTANCE.loadStandard(ConfigurationPrinter.class);
  if (3 != args.length) {
    System.err.println("Usage: " + ConfigurationPrinter.class.getName() + " <package.class.fieldname of a ConfigNamespace root> <output filename> <display mutabilities>");
    System.exit(-1);
  }
  final ConfigNamespace root=stringToNamespace(args[0]);
  final PrintStream stream=new PrintStream(new FileOutputStream(args[1]),false,UTF8_ENCODING);
  final boolean mutability=Boolean.valueOf(args[2]);
  new ConfigurationPrinter(stream,mutability).write(root);
  stream.flush();
  stream.close();
}

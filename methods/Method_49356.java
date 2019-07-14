public static void main(String args[]) throws IOException {
  int errors=filter(args[0],args[1]);
  System.exit(Math.min(errors,127));
}

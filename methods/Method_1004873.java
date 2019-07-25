protected void usage(String string){
  System.err.println(string);
  System.err.println();
  try {
    buildOptionParser().printHelpOn(System.err);
    System.exit(1);
  }
 catch (  IOException e) {
  }
}

private void run() throws Exception {
  String baseDir="src";
  check(new File(baseDir));
  if (hasError) {
    throw new Exception("Errors found");
  }
}

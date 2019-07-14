protected void printFooter(TestResult result){
  if (result.wasSuccessful()) {
    getWriter().println();
    getWriter().print("OK");
    getWriter().println(" (" + result.runCount() + " test" + (result.runCount() == 1 ? "" : "s") + ")");
  }
 else {
    getWriter().println();
    getWriter().println("FAILURES!!!");
    getWriter().println("Tests run: " + result.runCount() + ",  Failures: " + result.failureCount() + ",  Errors: " + result.errorCount());
  }
  getWriter().println();
}

protected void printFooter(Result result){
  if (result.wasSuccessful()) {
    getWriter().println();
    getWriter().print("OK");
    getWriter().println(" (" + result.getRunCount() + " test" + (result.getRunCount() == 1 ? "" : "s") + ")");
  }
 else {
    getWriter().println();
    getWriter().println("FAILURES!!!");
    getWriter().println("Tests run: " + result.getRunCount() + ",  Failures: " + result.getFailureCount());
  }
  getWriter().println();
}

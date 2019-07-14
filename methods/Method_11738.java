protected void printHeader(long runTime){
  getWriter().println();
  getWriter().println("Time: " + elapsedTimeAsString(runTime));
}

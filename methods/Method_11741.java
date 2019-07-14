protected void printDefectTrace(TestFailure booBoo){
  getWriter().print(BaseTestRunner.getFilteredTrace(booBoo.trace()));
}

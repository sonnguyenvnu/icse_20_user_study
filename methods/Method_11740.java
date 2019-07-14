protected void printDefectHeader(TestFailure booBoo,int count){
  getWriter().print(count + ") " + booBoo.failedTest());
}

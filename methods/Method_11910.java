protected void printFailure(Failure each,String prefix){
  getWriter().println(prefix + ") " + each.getTestHeader());
  getWriter().print(each.getTrimmedTrace());
}

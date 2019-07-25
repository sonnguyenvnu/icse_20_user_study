public static void Assert(boolean val){
  if (!val) {
    ReportBug("Assertion failure");
  }
  ;
}

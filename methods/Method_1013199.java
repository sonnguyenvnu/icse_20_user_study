public static void Assert(boolean val,String msg){
  if (!val) {
    ReportBug("Failure of assertion: " + msg);
  }
  ;
}

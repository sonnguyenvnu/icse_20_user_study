private static void Backspace(int n){
  ncol=ncol - n;
  if (ncol < 0) {
    Debug.ReportBug("TokenizeComment: Backspacing off end of line");
  }
  ;
}

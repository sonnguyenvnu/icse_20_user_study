protected void onGoTo(){
  if (currentPage instanceof LineNumberNavigable) {
    LineNumberNavigable lnn=(LineNumberNavigable)currentPage;
    goToController.show(lnn,lineNumber -> lnn.goToLineNumber(lineNumber));
  }
}

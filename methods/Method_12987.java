protected void onFindCriteriaChanged(){
  if (currentPage instanceof ContentSearchable) {
    mainView.setFindBackgroundColor(((ContentSearchable)currentPage).highlightText(mainView.getFindText(),mainView.getFindCaseSensitive()));
  }
}

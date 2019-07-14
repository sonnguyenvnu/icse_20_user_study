private void onRestoreInstanceState(Bundle inState){
  currentSection=inState.getInt(KEY_SECTION,SECTION_0);
  if (currentSection == SECTION_0) {
    loadSection0();
    reloadListeners();
  }
 else {
    loadSection1();
    reloadListeners();
  }
}

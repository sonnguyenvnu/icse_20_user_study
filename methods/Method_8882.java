public void switchMode(){
  if (selectedTool == 0) {
    blurControl.setVisibility(INVISIBLE);
    blurLayout.setVisibility(INVISIBLE);
    curveLayout.setVisibility(INVISIBLE);
    curvesControl.setVisibility(INVISIBLE);
    recyclerListView.setVisibility(VISIBLE);
  }
 else   if (selectedTool == 1) {
    recyclerListView.setVisibility(INVISIBLE);
    curveLayout.setVisibility(INVISIBLE);
    curvesControl.setVisibility(INVISIBLE);
    blurLayout.setVisibility(VISIBLE);
    if (blurType != 0) {
      blurControl.setVisibility(VISIBLE);
    }
    updateSelectedBlurType();
  }
 else   if (selectedTool == 2) {
    recyclerListView.setVisibility(INVISIBLE);
    blurLayout.setVisibility(INVISIBLE);
    blurControl.setVisibility(INVISIBLE);
    curveLayout.setVisibility(VISIBLE);
    curvesControl.setVisibility(VISIBLE);
    curvesToolValue.activeType=0;
    for (int a=0; a < 4; a++) {
      curveRadioButton[a].setChecked(a == 0,false);
    }
  }
}

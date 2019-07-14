/** 
 * Show tooltip on hover. 
 */
private void showMarkerHover(final int y){
  try {
    LineMarker m=findClosestMarker(y);
    if (m != null) {
      Problem p=m.problem;
      editor.statusToolTip(MarkerColumn.this,p.getMessage(),p.isError());
      setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
    }
  }
 catch (  Exception ex) {
    ex.printStackTrace();
  }
}

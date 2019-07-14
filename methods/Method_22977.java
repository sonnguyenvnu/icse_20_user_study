/** 
 * Find out which error/warning the user has clicked and scroll to it 
 */
private void scrollToMarkerAt(final int y){
  try {
    LineMarker m=findClosestMarker(y);
    if (m != null) {
      editor.highlight(m.problem);
    }
  }
 catch (  Exception ex) {
    ex.printStackTrace();
  }
}

/** 
 * On longpress, it finds the last or the first selected image before or after the targetIndex and selects them all.
 * @param
 */
public void selectAllUpTo(Media m){
  int targetIndex=media.indexOf(m);
  int indexRightBeforeOrAfter=-1;
  int indexNow;
  for (  Media sm : getSelected()) {
    indexNow=media.indexOf(sm);
    if (indexRightBeforeOrAfter == -1)     indexRightBeforeOrAfter=indexNow;
    if (indexNow > targetIndex)     break;
    indexRightBeforeOrAfter=indexNow;
  }
  if (indexRightBeforeOrAfter != -1) {
    for (int index=Math.min(targetIndex,indexRightBeforeOrAfter); index <= Math.max(targetIndex,indexRightBeforeOrAfter); index++) {
      if (media.get(index) != null) {
        if (media.get(index).setSelected(true)) {
          notifySelected(true);
          notifyItemChanged(index);
        }
      }
    }
  }
}

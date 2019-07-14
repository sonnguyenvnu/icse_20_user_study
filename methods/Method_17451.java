/** 
 * Moves the hot hand forward. 
 */
private void scanHot(){
  if (handHot == handTest) {
    scanTest();
  }
  if (handHot.status == Status.HOT) {
    if (handHot.marked) {
      handHot.marked=false;
    }
 else {
      handHot.status=Status.COLD;
      sizeCold++;
      sizeHot--;
    }
  }
  handHot=handHot.next;
}

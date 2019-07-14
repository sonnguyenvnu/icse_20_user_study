public void setNum(int num){
  if (num >= 0) {
    checkedText="" + (num + 1);
  }
 else   if (checkAnimator == null) {
    checkedText=null;
  }
  invalidate();
}

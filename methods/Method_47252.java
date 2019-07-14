public void updateHandlePosition(int vx,int l){
  if (vx != vx1) {
    setPadding(getPaddingLeft(),getPaddingTop(),getPaddingRight(),l + vx);
    setHandlePosition1(computeHandlePosition());
    vx1=vx;
  }
}

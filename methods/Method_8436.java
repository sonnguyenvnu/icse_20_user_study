public void setPageOffset(int position,float offset){
  progress=offset;
  scrollPosition=position;
  invalidate();
}

private void enable(int start,int end){
  mView.setNumberKeysEnabled(start,end);
  mAllNumberKeysDisabled=start == 0 && end == 0;
}

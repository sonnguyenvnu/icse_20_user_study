@Override public boolean next(){
  if (row == null || end) {
    row=null;
    return false;
  }
  end=true;
  return true;
}

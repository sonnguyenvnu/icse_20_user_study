public void setAllowDrawContent(boolean value){
  if (allowDrawContent != value) {
    allowDrawContent=value;
    invalidate();
  }
}

public void setAllowDrawContent(boolean value){
  if (allowDrawContent != value) {
    allowDrawContent=value;
    container.setBackgroundDrawable(allowDrawContent ? backDrawable : null);
    container.invalidate();
  }
}

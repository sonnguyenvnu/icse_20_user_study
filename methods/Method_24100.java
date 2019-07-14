protected boolean getHint(int which){
  if (which > 0) {
    return hints[which];
  }
 else {
    return !hints[-which];
  }
}

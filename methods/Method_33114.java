public void animateList(boolean expand){
  if ((expanded && !expand) || (!expanded && expand)) {
    animateList();
  }
}

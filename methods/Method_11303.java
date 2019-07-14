public void setProgressDescription(String progress){
  if (leftSB != null) {
    leftSB.setProgressHint(progress);
  }
  if (rightSB != null) {
    rightSB.setProgressHint(progress);
  }
}

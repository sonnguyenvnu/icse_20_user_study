@Override public void dispose(){
  this.control=null;
  if (showTransition != null) {
    showTransition.stop();
    showTransition.getKeyFrames().clear();
    showTransition=null;
  }
}

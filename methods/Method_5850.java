@Override public void run(){
  dispatchOnFrameAvailable();
  if (texture != null) {
    try {
      texture.updateTexImage();
    }
 catch (    RuntimeException e) {
    }
  }
}

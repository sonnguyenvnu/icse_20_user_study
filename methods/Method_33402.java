/** 
 * Called when the animation is starting
 */
protected void starting(){
  if (mementos != null) {
    for (int i=0; i < mementos.length; i++) {
      mementos[i].cache();
    }
  }
}

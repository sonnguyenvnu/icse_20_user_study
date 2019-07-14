/** 
 * this method will pause the timer and reverse the animation if the timer already started otherwise it will start the animation.
 */
public void reverseAndContinue(){
  if (isRunning()) {
    super.stop();
    for (    AnimationHandler handler : animationHandlers) {
      handler.reverse(totalElapsedMilliseconds);
    }
    startTime=-1;
    super.start();
  }
 else {
    start();
  }
}

/** 
 * ????????? {@link SuperTextView#setAutoAdjust(boolean)}?true?????
 */
public void startAnim(){
  needRun=true;
  runnable=false;
  if (animThread == null) {
    checkWhetherNeedInitInvalidate();
    needRun=true;
    runnable=true;
    if (handleAnim == null) {
      initHandleAnim();
    }
    animThread=new Thread(handleAnim);
    animThread.start();
  }
}

/** 
 * Changes expanded state to expanded and returns whether operation succeed or not. Operation might fail in case state change is disabled for some reason.
 * @param animate whether animate state change transition or not
 * @return true if operation succeed, false otherwise
 */
public boolean expand(final boolean animate){
  if (expanded || !isStateChangeEnabled()) {
    return false;
  }
  stopAnimation();
  expanded=true;
  setStateIcons();
  if (content != null) {
    content.setVisible(true);
  }
  updateHeaderSides();
  fireExpanding();
  if (animate && isShowing()) {
    animator=new WebTimer("WebCollapsiblePane.expandTimer",StyleConstants.fastAnimationDelay,new ActionListener(){
      @Override public void actionPerformed(      final ActionEvent e){
        if (transitionProgress < 1f) {
          transitionProgress=Math.min(1f,transitionProgress + expandSpeed);
          revalidate();
        }
 else {
          transitionProgress=1f;
          finishExpandAction();
          animator.stop();
        }
      }
    }
);
    animator.start();
  }
 else {
    transitionProgress=1f;
    finishExpandAction();
  }
  return true;
}

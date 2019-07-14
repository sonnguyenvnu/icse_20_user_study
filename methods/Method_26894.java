/** 
 * Enters this scene, which entails changing all values that are specified by this scene. These may be values associated with a layout view group or layout resource file which will now be added to the scene root, or it may be values changed by an  {@link #setEnterAction(Runnable)} enter action}, or acombination of the these. No transition will be run when the scene is entered. To get transition behavior in scene changes, use one of the methods in  {@link TransitionManager} instead.
 */
public void enter(){
  if (mLayoutId > 0 || mLayout != null) {
    getSceneRoot().removeAllViews();
    if (mLayoutId > 0) {
      LayoutInflater.from(mContext).inflate(mLayoutId,mSceneRoot);
    }
 else {
      mSceneRoot.addView(mLayout);
    }
  }
  if (mEnterAction != null) {
    mEnterAction.run();
  }
  setCurrentScene(mSceneRoot,this);
}

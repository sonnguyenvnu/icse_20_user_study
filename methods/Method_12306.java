/** 
 * Sets whether this drawable is visible. If rendering of next frame is scheduled on draw current one (the default) then this method only calls through to the super class's implementation.<br> Otherwise (if  {@link GifDrawableBuilder#setRenderingTriggeredOnDraw(boolean)} was used with <code>true</code>)when the drawable becomes invisible, it will pause its animation. A subsequent change to visible with <code>restart</code> set to true will restart the animation from the first frame. If <code>restart</code> is false, the animation will resume from the most recent frame.
 * @param visible true if visible, false otherwise
 * @param restart when visible and rendering is triggered on draw, true to force the animation to restartfrom the first frame
 * @return true if the new visibility is different than its previous state
 */
@Override public boolean setVisible(boolean visible,boolean restart){
  final boolean changed=super.setVisible(visible,restart);
  if (!mIsRenderingTriggeredOnDraw) {
    if (visible) {
      if (restart) {
        reset();
      }
      if (changed) {
        start();
      }
    }
 else     if (changed) {
      stop();
    }
  }
  return changed;
}

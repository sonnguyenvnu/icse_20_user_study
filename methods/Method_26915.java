private void loadTransition(@NonNull AttributeSet attrs,@NonNull ViewGroup sceneRoot,@NonNull TransitionManager transitionManager) throws Resources.NotFoundException {
  TypedArray a=mContext.obtainStyledAttributes(attrs,R.styleable.TransitionManager);
  int transitionId=a.getResourceId(R.styleable.TransitionManager_transition,-1);
  int fromId=a.getResourceId(R.styleable.TransitionManager_fromScene,-1);
  Scene fromScene=(fromId < 0) ? null : Scene.getSceneForLayout(sceneRoot,fromId,mContext);
  int toId=a.getResourceId(R.styleable.TransitionManager_toScene,-1);
  Scene toScene=(toId < 0) ? null : Scene.getSceneForLayout(sceneRoot,toId,mContext);
  if (transitionId >= 0) {
    Transition transition=inflateTransition(transitionId);
    if (transition != null) {
      if (toScene == null) {
        throw new RuntimeException("No toScene for transition ID " + transitionId);
      }
      if (fromScene == null) {
        transitionManager.setTransition(toScene,transition);
      }
 else {
        transitionManager.setTransition(fromScene,toScene,transition);
      }
    }
  }
  a.recycle();
}

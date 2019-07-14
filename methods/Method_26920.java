/** 
 * Convenience method to animate to a new scene defined by all changes within the given scene root between calling this method and the next rendering frame. Calling this method causes TransitionManager to capture current values in the scene root and then post a request to run a transition on the next frame. At that time, the new values in the scene root will be captured and changes will be animated. There is no need to create a Scene; it is implied by changes which take place between calling this method and the next frame when the transition begins. <p/> <p>Calling this method several times before the next frame (for example, if unrelated code also wants to make dynamic changes and run a transition on the same scene root), only the first call will trigger capturing values and exiting the current scene. Subsequent calls to the method with the same scene root during the same frame will be ignored.</p> <p/> <p>Passing in <code>null</code> for the transition parameter will cause the TransitionManager to use its default transition.</p>
 * @param sceneRoot  The root of the View hierarchy to run the transition on.
 * @param transition The transition to use for this change. Avalue of null causes the TransitionManager to use the default transition.
 */
public static void beginDelayedTransition(final @NonNull ViewGroup sceneRoot,@Nullable Transition transition){
  if (!sPendingTransitions.contains(sceneRoot) && ViewUtils.isLaidOut(sceneRoot,true)) {
    if (Transition.DBG) {
      Log.d(LOG_TAG,"beginDelayedTransition: root, transition = " + sceneRoot + ", " + transition);
    }
    sPendingTransitions.add(sceneRoot);
    if (transition == null) {
      transition=sDefaultTransition;
    }
    final Transition transitionClone=transition.clone();
    sceneChangeSetup(sceneRoot,transitionClone);
    Scene.setCurrentScene(sceneRoot,null);
    sceneChangeRunTransition(sceneRoot,transitionClone);
  }
}

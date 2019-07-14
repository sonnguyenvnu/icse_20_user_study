/** 
 * This is where all of the work of a transition/scene-change is orchestrated. This method captures the start values for the given transition, exits the current Scene, enters the new scene, captures the end values for the transition, and finally plays the resulting values-populated transition.
 * @param scene      The scene being entered
 * @param transition The transition to play for this scene change
 */
private static void changeScene(@NonNull Scene scene,@Nullable Transition transition){
  final ViewGroup sceneRoot=scene.getSceneRoot();
  if (!sPendingTransitions.contains(sceneRoot)) {
    Transition transitionClone=null;
    if (isTransitionsAllowed()) {
      sPendingTransitions.add(sceneRoot);
      if (transition != null) {
        transitionClone=transition.clone();
        transitionClone.setSceneRoot(sceneRoot);
      }
      Scene oldScene=Scene.getCurrentScene(sceneRoot);
      if (oldScene != null && transitionClone != null && oldScene.isCreatedFromLayoutResource()) {
        transitionClone.setCanRemoveViews(true);
      }
    }
    sceneChangeSetup(sceneRoot,transitionClone);
    scene.enter();
    sceneChangeRunTransition(sceneRoot,transitionClone);
  }
}

/** 
 * Sets a specific transition to occur when the given pair of scenes is exited/entered.
 * @param fromScene  The scene being exited when the given transition willbe run
 * @param toScene    The scene being entered when the given transition willbe run
 * @param transition The transition that will play when the given scene isentered. A value of null will result in the default behavior of using the default transition instead.
 */
public void setTransition(@NonNull Scene fromScene,@NonNull Scene toScene,@Nullable Transition transition){
  ArrayMap<Scene,Transition> sceneTransitionMap=mScenePairTransitions.get(toScene);
  if (sceneTransitionMap == null) {
    sceneTransitionMap=new ArrayMap<Scene,Transition>();
    mScenePairTransitions.put(toScene,sceneTransitionMap);
  }
  sceneTransitionMap.put(fromScene,transition);
}

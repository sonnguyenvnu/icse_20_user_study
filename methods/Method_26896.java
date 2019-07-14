/** 
 * Returns whether this Scene was created by a layout resource file, determined by the layoutId passed into {@link #getSceneForLayout(android.view.ViewGroup,int,android.content.Context)}. This is called by TransitionManager to determine whether it is safe for views from this scene to be removed from their parents when the scene is exited, which is used by  {@link Fade} to fade these views out (the views must be removed fromtheir parent in order to add them to the overlay for fading purposes). If a Scene is not based on a resource file, then the impact of removing views arbitrarily is unknown and should be avoided.
 */
boolean isCreatedFromLayoutResource(){
  return (mLayoutId > 0);
}

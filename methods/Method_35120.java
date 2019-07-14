/** 
 * The transition will be delayed until the view with the name passed in is available in the "to" hierarchy. This is particularly useful for views that don't load instantly, like RecyclerViews. Note that using this method can potentially lock up your app indefinitely if the view never loads!
 */
protected final void waitOnSharedElementNamed(@NonNull String name){
  if (!sharedElementNames.values().contains(name)) {
    throw new IllegalStateException("Can't wait on a shared element that hasn't been registered using addSharedElement");
  }
  waitForTransitionNames.add(name);
}

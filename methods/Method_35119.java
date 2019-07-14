/** 
 * Used to register an element that will take part in the shared element transition.
 * @param name The transition name that is used for both the entering and exiting views.
 */
protected final void addSharedElement(@NonNull String name){
  sharedElementNames.put(name,name);
}

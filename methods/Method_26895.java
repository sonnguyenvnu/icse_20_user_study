/** 
 * Gets the current  {@link android.transition.Scene} set on the given view. A scene is set on a viewonly if that view is the scene root.
 * @return The current Scene set on this view. A value of null indicates thatno Scene is currently set.
 */
@Nullable public static Scene getCurrentScene(@NonNull View view){
  return (Scene)view.getTag(R.id.current_scene);
}

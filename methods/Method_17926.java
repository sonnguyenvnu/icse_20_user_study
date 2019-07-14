/** 
 * Create the object that will be mounted in the  {@link LithoView}.
 * @param context The {@link Context} to be used to create the content.
 * @return an Object that can be mounted for this component.
 */
protected Object onCreateMountContent(Context context){
  throw new RuntimeException("Trying to mount a MountSpec that doesn't implement @OnCreateMountContent");
}

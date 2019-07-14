/** 
 * Creates a new instance of DraweeHolder that detaches / attaches controller whenever context notifies it about activity's onStop and onStart callbacks.
 */
public static <DH extends DraweeHierarchy>DraweeHolder<DH> create(@Nullable DH hierarchy,Context context){
  DraweeHolder<DH> holder=new DraweeHolder<DH>(hierarchy);
  holder.registerWithContext(context);
  return holder;
}

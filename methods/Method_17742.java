@GuardedBy("this") @Nullable private static Map<String,Component> composeDetach(@Nullable Map<String,Component> attachable,@Nullable Map<String,Component> attached){
  Map<String,Component> toDetach=null;
  if (attached != null) {
    toDetach=new HashMap<>(attached);
    if (attachable != null) {
      toDetach.keySet().removeAll(attachable.keySet());
    }
  }
  return toDetach;
}

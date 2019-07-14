@GuardedBy("this") @Nullable private static Map<String,Component> composeAttach(@Nullable Map<String,Component> attachable,@Nullable Map<String,Component> attached){
  Map<String,Component> toAttach=null;
  if (attachable != null) {
    toAttach=new HashMap<>(attachable);
    if (attached != null) {
      toAttach.keySet().removeAll(attached.keySet());
    }
  }
  return toAttach;
}

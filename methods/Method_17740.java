/** 
 * Execute  {@link OnAttached} method for components in the set of given attachable minus {@link #mAttached}; execute  {@link OnDetached} method for components in the set of {@link #mAttached}minus given attachable.
 * @param attachable contains components that have implemented {@link OnAttached} or {@link OnDetached} delegate methods.
 */
void onAttached(@Nullable Map<String,Component> attachable){
  @Nullable final Map<String,Component> toAttach;
  @Nullable final Map<String,Component> toDetach;
synchronized (this) {
    toAttach=composeAttach(attachable,mAttached);
    toDetach=composeDetach(attachable,mAttached);
    if (attachable != null) {
      mAttached=new HashMap<>(attachable);
    }
 else {
      mAttached=null;
    }
  }
  if (toDetach != null) {
    for (    Component component : toDetach.values()) {
      component.onDetached(component.getScopedContext());
    }
  }
  if (toAttach != null) {
    for (    Component component : toAttach.values()) {
      component.onAttached(component.getScopedContext());
    }
  }
}

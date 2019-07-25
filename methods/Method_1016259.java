public static void install(Component tracked,Component component,Boolean focusedVisible,Boolean mouseoverVisible){
  ConditionalVisibilityListener cvl=new ConditionalVisibilityListener(component,focusedVisible,mouseoverVisible);
  if (focusedVisible != null) {
    tracked.addFocusListener(cvl);
  }
  if (mouseoverVisible != null) {
    tracked.addMouseListener(cvl);
  }
}

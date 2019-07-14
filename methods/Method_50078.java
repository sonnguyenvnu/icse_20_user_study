/** 
 * Create an observable of the open state of the drawer of  {@code view}. <p> <em>Warning:</em> The created observable keeps a strong reference to  {@code view}. Unsubscribe to free this reference. <p> <em>Note:</em> A value will be emitted immediately on subscribe.
 */
@CheckResult @NonNull public static InitialValueObservable<Boolean> drawerOpen(@NonNull DrawerLayout view,int gravity){
  checkNotNull(view,"view == null");
  return new DrawerLayoutDrawerOpenedObservable(view,gravity);
}

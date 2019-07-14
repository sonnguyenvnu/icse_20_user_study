/** 
 * An action which sets whether the drawer with  {@code gravity} of {@code view} is open.<p> <em>Warning:</em> The created observable keeps a strong reference to  {@code view}. Unsubscribe to free this reference.
 */
@CheckResult @NonNull public static Consumer<? super Boolean> open(@NonNull final DrawerLayout view,final int gravity){
  checkNotNull(view,"view == null");
  return new Consumer<Boolean>(){
    @Override public void accept(    Boolean aBoolean){
      if (aBoolean) {
        view.openDrawer(gravity);
      }
 else {
        view.closeDrawer(gravity);
      }
    }
  }
;
}

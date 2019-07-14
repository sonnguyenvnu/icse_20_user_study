/** 
 * ??popu????
 */
@SuppressLint("RestrictedApi") @Override protected boolean onPrepareOptionsPanel(View view,Menu menu){
  if (menu != null) {
    if (menu.getClass().getSimpleName().equals("MenuBuilder")) {
      try {
        Method m=menu.getClass().getDeclaredMethod("setOptionalIconsVisible",Boolean.TYPE);
        m.setAccessible(true);
        m.invoke(menu,true);
      }
 catch (      Exception e) {
        Log.e(getClass().getSimpleName(),"onMenuOpened...unable to set icons for overflow menu",e);
      }
    }
  }
  return super.onPrepareOptionsPanel(view,menu);
}

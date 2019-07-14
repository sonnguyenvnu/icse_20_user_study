private void clearCurrentState(){
  SharedPreferences preferences=ApplicationLoader.applicationContext.getSharedPreferences("logininfo2",Context.MODE_PRIVATE);
  SharedPreferences.Editor editor=preferences.edit();
  editor.clear();
  editor.commit();
}

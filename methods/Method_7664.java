@Keep public void setDrawerPosition(float value){
  drawerPosition=value;
  if (drawerPosition > drawerLayout.getMeasuredWidth()) {
    drawerPosition=drawerLayout.getMeasuredWidth();
  }
 else   if (drawerPosition < 0) {
    drawerPosition=0;
  }
  drawerLayout.setTranslationX(drawerPosition);
  final int newVisibility=drawerPosition > 0 ? VISIBLE : GONE;
  if (drawerLayout.getVisibility() != newVisibility) {
    drawerLayout.setVisibility(newVisibility);
  }
  setScrimOpacity(drawerPosition / (float)drawerLayout.getMeasuredWidth());
}

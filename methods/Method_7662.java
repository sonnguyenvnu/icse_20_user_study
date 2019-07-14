public void setDrawerLayout(ViewGroup layout){
  drawerLayout=layout;
  addView(drawerLayout);
  if (Build.VERSION.SDK_INT >= 21) {
    drawerLayout.setFitsSystemWindows(true);
  }
}

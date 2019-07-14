public static void setNavigationItemTint(NavigationView navigationView,int color){
  Context context=navigationView.getContext();
  navigationView.setItemIconTintList(createNavigationItemTintList(android.R.attr.textColorSecondary,color,context));
  navigationView.setItemTextColor(createNavigationItemTintList(android.R.attr.textColorPrimary,color,context));
}

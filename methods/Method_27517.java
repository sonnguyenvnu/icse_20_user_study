private void setupDrawer(){
  if (drawer != null && !(this instanceof MainActivity)) {
    if (!PrefGetter.isNavDrawerHintShowed()) {
      drawer.getViewTreeObserver().addOnPreDrawListener(new ViewTreeObserver.OnPreDrawListener(){
        @Override public boolean onPreDraw(){
          drawer.openDrawer(GravityCompat.START);
          drawer.addDrawerListener(new DrawerLayout.SimpleDrawerListener(){
            @Override public void onDrawerOpened(            View drawerView){
              super.onDrawerOpened(drawerView);
              drawerView.postDelayed(() -> {
                if (drawer != null) {
                  closeDrawer();
                  drawer.removeDrawerListener(this);
                }
              }
,1000);
            }
          }
);
          drawer.getViewTreeObserver().removeOnPreDrawListener(this);
          return true;
        }
      }
);
    }
  }
}

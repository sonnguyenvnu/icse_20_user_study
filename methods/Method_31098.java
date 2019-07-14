public static void postOnDrawerClosed(@NonNull DrawerLayout drawerLayout,@NonNull Runnable runnable){
  drawerLayout.addDrawerListener(new DrawerLayout.SimpleDrawerListener(){
    @Override public void onDrawerClosed(    View drawerView){
      drawerLayout.removeDrawerListener(this);
      runnable.run();
    }
  }
);
}

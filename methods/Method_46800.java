void initialiseFab(){
  int colorAccent=getAccent();
  floatingActionButton=findViewById(R.id.fabs_menu);
  floatingActionButton.getMenuButton().setBackgroundColor(colorAccent);
  floatingActionButton.getMenuButton().setRippleColor(Utils.getColor(this,R.color.white_translucent));
  floatingActionButton.setAnimationDuration(500);
  floatingActionButton.setMenuListener(new FABsMenuListener(){
    @Override public void onMenuExpanded(    FABsMenu fabsMenu){
      showSmokeScreen();
    }
    @Override public void onMenuCollapsed(    FABsMenu fabsMenu){
      hideSmokeScreen();
    }
  }
);
  floatingActionButton.setMenuListener(new FABsMenuListener(){
    @Override public void onMenuExpanded(    FABsMenu fabsMenu){
      FileUtils.revealShow(fabBgView,true);
    }
    @Override public void onMenuCollapsed(    FABsMenu fabsMenu){
      FileUtils.revealShow(fabBgView,false);
    }
  }
);
  initFabTitle(findViewById(R.id.menu_new_folder),MainActivityHelper.NEW_FOLDER);
  initFabTitle(findViewById(R.id.menu_new_file),MainActivityHelper.NEW_FILE);
  initFabTitle(findViewById(R.id.menu_new_cloud),MainActivityHelper.NEW_CLOUD);
}

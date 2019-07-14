private void askPassword(){
  Security.authenticateUser(MainActivity.this,new Security.AuthCallBack(){
    @Override public void onAuthenticated(){
      closeDrawer();
      selectNavigationItem(NAVIGATION_ITEM_HIDDEN_FOLDERS);
      displayAlbums(true);
    }
    @Override public void onError(){
      Toast.makeText(getApplicationContext(),R.string.wrong_password,Toast.LENGTH_SHORT).show();
    }
  }
);
}

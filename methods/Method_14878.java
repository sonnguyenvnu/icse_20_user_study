@Override public void onDataChanged(){
  tvBaseTitle.setText(isOnEditMode ? "????" : (isCurrentUser(id) ? "????" : "????"));
  if (bottomMenuView != null) {
    bottomMenuView.bindView(MenuUtil.getMenuList(MenuUtil.USER,id,!User.isFriend(currentUser,id)));
  }
  runThread(TAG + "onDataChanged",new Runnable(){
    @Override public void run(){
      user=CacheManager.getInstance().get(User.class,"" + id);
      if (isOnEditMode == false) {
        momentList=CacheManager.getInstance().getList(Moment.class,"userId=" + id,0,3);
      }
      runUiThread(new Runnable(){
        @Override public void run(){
          setUser(user,momentList);
          setPrivacy(null);
        }
      }
);
      HttpRequest.getUser(id,!isOnEditMode,HTTP_GET,UserActivity.this);
      HttpRequest.getPrivacy(id,HTTP_GET_PRIVACY,UserActivity.this);
    }
  }
);
}

@Override public void initData(){
  ivQRCodeProgress.setVisibility(View.VISIBLE);
  runThread(TAG + "initData",new Runnable(){
    @Override public void run(){
      user=CacheManager.getInstance().get(User.class,"" + userId);
      if (user == null) {
        user=new User(userId);
      }
      runUiThread(new Runnable(){
        @Override public void run(){
          ImageLoaderUtil.loadImage(ivQRCodeHead,user.getHead());
          tvQRCodeName.setText(StringUtil.getTrimedString(user.getName()));
        }
      }
);
      setQRCode(user);
    }
  }
);
}

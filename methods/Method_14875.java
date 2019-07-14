@Override public void initView(){
  llUserBusinessCardContainer=(ViewGroup)findViewById(R.id.llUserBusinessCardContainer);
  llUserBusinessCardContainer.removeAllViews();
  userView=new UserView(context,getResources());
  llUserBusinessCardContainer.addView(userView.createView(getLayoutInflater()));
  llUserMoment=findViewById(R.id.llUserMoment);
  gvUserMoment=(GridView)findViewById(R.id.gvUserMoment);
  tvUserRemark=(TextView)findViewById(R.id.tvUserRemark);
  tvUserPhone=(TextView)findViewById(R.id.tvUserPhone);
  llUserMoment.setVisibility(isOnEditMode ? View.GONE : View.VISIBLE);
  if (isOnEditMode == false) {
    llUserBottomMenuContainer=(ViewGroup)findViewById(R.id.llUserBottomMenuContainer);
    llUserBottomMenuContainer.removeAllViews();
    bottomMenuView=new BottomMenuView(context,getResources(),REQUEST_TO_BOTTOM_MENU);
    llUserBottomMenuContainer.addView(bottomMenuView.createView(getLayoutInflater()));
  }
}

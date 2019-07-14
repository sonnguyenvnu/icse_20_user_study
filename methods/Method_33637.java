/** 
 * inflateHeaderView ?????????
 */
private void initDrawerLayout(){
  navView.inflateHeaderView(R.layout.nav_header_main);
  View headerView=navView.getHeaderView(0);
  bind=DataBindingUtil.bind(headerView);
  bind.setListener(this);
  bind.dayNightSwitch.setChecked(SPUtils.getNightMode());
  isReadOk.set(SPUtils.isRead());
  GlideUtil.displayCircle(bind.ivAvatar,ConstantsImageUrl.IC_AVATAR);
  bind.llNavExit.setOnClickListener(this);
  bind.ivAvatar.setOnClickListener(this);
  bind.llNavHomepage.setOnClickListener(listener);
  bind.llNavScanDownload.setOnClickListener(listener);
  bind.llNavDeedback.setOnClickListener(listener);
  bind.llNavAbout.setOnClickListener(listener);
  bind.llNavLogin.setOnClickListener(listener);
  bind.llNavCollect.setOnClickListener(listener);
}

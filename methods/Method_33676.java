public void getWanAndroidBanner(){
  viewModel.getWanAndroidBanner().observe(this,new Observer<WanAndroidBannerBean>(){
    @Override public void onChanged(    @Nullable WanAndroidBannerBean bean){
      if (bean != null) {
        androidBinding.rlBanner.setVisibility(View.VISIBLE);
        showBannerView(bean.getData());
      }
 else {
        androidBinding.rlBanner.setVisibility(View.GONE);
      }
      getHomeList();
    }
  }
);
}

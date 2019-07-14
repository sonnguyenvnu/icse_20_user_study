private void onObserveViewModel(){
  viewModel.getShowLoading().observe(this,new Observer<Boolean>(){
    @Override public void onChanged(    @Nullable Boolean aBoolean){
      showRotaLoading(aBoolean);
    }
  }
);
  viewModel.getBannerData().observe(this,new Observer<EverydayViewModel.BannerDataBean>(){
    @Override public void onChanged(    @Nullable EverydayViewModel.BannerDataBean bean){
      if (bean != null && bean.getImageUrls() != null && bean.getImageUrls().size() > 0) {
        mHeaderBinding.banner.setImages(bean.getImageUrls()).setImageLoader(new GlideImageLoader()).start();
        ArrayList<BannerItemBean> list=bean.getList();
        if (list != null && list.size() > 0) {
          mHeaderBinding.banner.setOnBannerListener(position -> {
            if (!TextUtils.isEmpty(list.get(position).getCode()) && list.get(position).getCode().startsWith("http")) {
              WebViewActivity.loadUrl(getContext(),list.get(position).getCode(),"???...");
            }
          }
);
        }
        isLoadBanner=true;
      }
    }
  }
);
  viewModel.getContentData().observe(this,new Observer<ArrayList<List<AndroidBean>>>(){
    @Override public void onChanged(    @Nullable ArrayList<List<AndroidBean>> lists){
      if (lists != null && lists.size() > 0) {
        mEverydayAdapter.clear();
        mEverydayAdapter.addAll(lists);
        mEverydayAdapter.notifyDataSetChanged();
        bindingView.recyclerView.refreshComplete();
      }
 else {
        showError();
      }
    }
  }
);
}

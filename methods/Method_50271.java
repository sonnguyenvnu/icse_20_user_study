@Override public void onItemClick(View view,int position){
  BucketBean bucketBean=mBucketBeanList.get(position);
  String bucketId=bucketBean.getBucketId();
  mRlBucektOverview.setVisibility(View.GONE);
  if (TextUtils.equals(mBucketId,bucketId)) {
    return;
  }
  mBucketId=bucketId;
  EmptyViewUtils.showLoading(mLlEmptyView);
  mRvMedia.setHasLoadMore(false);
  mMediaBeanList.clear();
  mMediaGridAdapter.notifyDataSetChanged();
  mTvFolderName.setText(bucketBean.getBucketName());
  mBucketAdapter.setSelectedBucket(bucketBean);
  mRvMedia.setFooterViewHide(true);
  mPage=1;
  mMediaGridPresenter.getMediaList(mBucketId,mPage,LIMIT);
}

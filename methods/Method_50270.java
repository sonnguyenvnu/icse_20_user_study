@Override public void onRequestBucketCallback(List<BucketBean> list){
  if (list == null || list.size() == 0) {
    return;
  }
  mBucketBeanList.addAll(list);
  mBucketAdapter.setSelectedBucket(list.get(0));
}

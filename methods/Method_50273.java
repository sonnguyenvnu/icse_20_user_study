public void onObItemClick(int position){
  MediaBean mediaBean=mMediaBeanList.get(position);
  if (mediaBean.getId() == Integer.MIN_VALUE) {
    if (!CameraUtils.hasCamera(getContext())) {
      Toast.makeText(getContext(),R.string.gallery_device_no_camera_tips,Toast.LENGTH_SHORT).show();
      return;
    }
    boolean b=PermissionCheckUtils.checkCameraPermission(mMediaActivity,requestStorageAccessPermissionTips,MediaActivity.REQUEST_CAMERA_ACCESS_PERMISSION);
    if (b) {
      openCamera(mMediaActivity);
    }
  }
 else {
    if (mConfiguration.isRadio()) {
      if (mConfiguration.isImage()) {
        radioNext(mediaBean);
      }
 else {
        videoRadioNext(mediaBean);
      }
    }
 else {
      MediaBean firstBean=mMediaBeanList.get(0);
      ArrayList<MediaBean> gridMediaList=new ArrayList<>();
      gridMediaList.addAll(mMediaBeanList);
      int pos=position;
      if (firstBean.getId() == Integer.MIN_VALUE) {
        pos=position - 1;
        gridMediaList.clear();
        List<MediaBean> list=mMediaBeanList.subList(1,mMediaBeanList.size());
        gridMediaList.addAll(list);
      }
      RxBus.getDefault().post(new OpenMediaPageFragmentEvent(gridMediaList,pos));
    }
  }
}

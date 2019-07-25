@OnClick(R.id.minimizeImageView) public void minimize(){
  gEngineKit.getCurrentSession().stopVideoSource();
  ((SingleVoipCallActivity)getActivity()).showFloatingView();
}

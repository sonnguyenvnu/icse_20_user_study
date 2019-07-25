private void deal(){
  display.release();
  display.setState(DefaultVideoPlayer.PLAY_STATE_PREPARING);
  if (data.getUrl() != null) {
    presenter.getDetailData(data.getUrl(),((DefaultVideoController)display.getController()).isSwitchUrl());
  }
  presenter.getDetailInfo(data.getId());
}

private void init(){
  gEngineKit=((SingleVoipCallActivity)getActivity()).getEngineKit();
  if (gEngineKit.getCurrentSession() != null && gEngineKit.getCurrentSession().getState() == AVEngineKit.CallState.Connected) {
    descTextView.setVisibility(View.GONE);
    outgoingActionContainer.setVisibility(View.VISIBLE);
    durationTextView.setVisibility(View.VISIBLE);
  }
 else {
    if (((SingleVoipCallActivity)getActivity()).isOutgoing()) {
      descTextView.setText(R.string.av_waiting);
      outgoingActionContainer.setVisibility(View.VISIBLE);
      incomingActionContainer.setVisibility(View.GONE);
    }
 else {
      descTextView.setText(R.string.av_audio_invite);
      outgoingActionContainer.setVisibility(View.GONE);
      incomingActionContainer.setVisibility(View.VISIBLE);
    }
  }
  String targetId=((SingleVoipCallActivity)getActivity()).getTargetId();
  UserInfo userInfo=ChatManager.Instance().getUserInfo(targetId,false);
  Glide.with(this).load(userInfo.portrait).into(portraitImageView);
  UserViewModel userViewModel=WfcUIKit.getAppScopeViewModel(UserViewModel.class);
  nameTextView.setText(userViewModel.getUserDisplayName(userInfo));
  muteImageView.setSelected(!micEnabled);
  updateCallDuration();
}

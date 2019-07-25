@OnClick(R.id.acceptImageView) public void accept(){
  AVEngineKit.CallSession session=gEngineKit.getCurrentSession();
  if (session != null && session.getState() == AVEngineKit.CallState.Incoming) {
    session.answerCall(false);
  }
 else {
    getActivity().finish();
  }
}

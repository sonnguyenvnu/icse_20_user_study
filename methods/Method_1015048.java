private void init(String targetId,boolean outgoing,boolean audioOnly){
  AVEngineKit.CallSession session=gEngineKit.getCurrentSession();
  Fragment fragment;
  if (audioOnly) {
    fragment=new AudioFragment();
  }
 else {
    fragment=new VideoFragment();
  }
  currentCallback=(AVEngineKit.CallSessionCallback)fragment;
  FragmentManager fragmentManager=getSupportFragmentManager();
  fragmentManager.beginTransaction().add(android.R.id.content,fragment).commit();
  if (outgoing) {
    gEngineKit.startCall(targetId,audioOnly,SingleVoipCallActivity.this);
    gEngineKit.startPreview();
  }
 else {
    if (session == null) {
      finish();
    }
 else {
      session.setCallback(SingleVoipCallActivity.this);
    }
  }
}

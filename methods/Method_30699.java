@Subscribe(threadMode=ThreadMode.POSTING) public void onSetEnabled(SetEnabledEvent event){
  setEnabledForActivity(event.enabled);
}

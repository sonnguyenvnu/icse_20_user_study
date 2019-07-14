private void updateLastSync(Long timestamp){
  prefs.setLastSync(timestamp + 1);
}

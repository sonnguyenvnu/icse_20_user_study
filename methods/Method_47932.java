public void incrementLaunchCount(){
  storage.putInt("launch_count",getLaunchCount() + 1);
}

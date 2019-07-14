public Failover asPlayback(){
  if (this.status == null) {
    return playback(file);
  }
  return playback(file,this.status);
}

private int getDuration(int progress){
  return (int)(getCurrentSongDuration() * ((float)progress / seekBarProgress.getMax()));
}

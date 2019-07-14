public long getTimescale(Mp4Movie mp4Movie){
  long timescale=0;
  if (!mp4Movie.getTracks().isEmpty()) {
    timescale=mp4Movie.getTracks().iterator().next().getTimeScale();
  }
  for (  Track track : mp4Movie.getTracks()) {
    timescale=gcd(track.getTimeScale(),timescale);
  }
  return timescale;
}

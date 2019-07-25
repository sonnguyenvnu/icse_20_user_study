public void play(int track){
  if (title == null) {
    System.out.println(description + " can't play track " + currentTrack + ", no cd inserted");
  }
 else {
    currentTrack=track;
    System.out.println(description + " playing track " + currentTrack);
  }
}

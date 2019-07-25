public void play(int track){
  if (movie == null) {
    System.out.println(description + " can't play track " + track + " no dvd inserted");
  }
 else {
    currentTrack=track;
    System.out.println(description + " playing track " + currentTrack + " of \"" + movie + "\"");
  }
}

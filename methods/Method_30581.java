public List<String> getArtistNames(){
  return Functional.map(artists,artist -> artist.name);
}

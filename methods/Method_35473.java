public static Bitmap parseAlbum(Song song){
  return parseAlbum(new File(song.getPath()));
}

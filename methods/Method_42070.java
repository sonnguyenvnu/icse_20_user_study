public static Album getEmptyAlbum(){
  Album album=new Album(null,null);
  album.settings=AlbumSettings.getDefaults();
  return album;
}

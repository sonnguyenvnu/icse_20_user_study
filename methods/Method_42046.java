public void removeAlbumsThatStartsWith(String path){
  if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N)   albums.removeIf(album -> album.getPath().startsWith(path));
 else {
    Iterator<Album> iter=albums.iterator();
    while (iter.hasNext()) {
      Album album=iter.next();
      if (album.getPath().startsWith(path))       iter.remove();
    }
  }
  notifyDataSetChanged();
}

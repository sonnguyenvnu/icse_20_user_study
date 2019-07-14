public void removeSelectedAlbums(){
  if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N)   albums.removeIf(Album::isSelected);
 else {
    Iterator<Album> iter=albums.iterator();
    while (iter.hasNext()) {
      Album album=iter.next();
      if (album.isSelected())       iter.remove();
    }
  }
  selectedCount=0;
  notifyDataSetChanged();
}

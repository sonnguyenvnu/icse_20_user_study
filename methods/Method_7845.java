private TLRPC.Photo getPhotoWithId(long id){
  if (currentPage == null || currentPage.cached_page == null) {
    return null;
  }
  if (currentPage.photo != null && currentPage.photo.id == id) {
    return currentPage.photo;
  }
  for (int a=0; a < currentPage.cached_page.photos.size(); a++) {
    TLRPC.Photo photo=currentPage.cached_page.photos.get(a);
    if (photo.id == id) {
      return photo;
    }
  }
  return null;
}

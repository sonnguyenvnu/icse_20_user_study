@Finder("titleAndOrFormat") public List<Photo> find(@PagingContextParam PagingContext pagingContext,@QueryParam("title") @Optional String title,@QueryParam("format") @Optional PhotoFormats format){
  final List<Photo> photos=new ArrayList<Photo>();
  int index=0;
  final int begin=pagingContext.getStart();
  final int end=begin + pagingContext.getCount();
  final Collection<Photo> dbPhotos=_db.getData().values();
  for (  Photo p : dbPhotos) {
    if (index == end) {
      break;
    }
 else     if (index >= begin) {
      if (title == null || p.getTitle().equalsIgnoreCase(title)) {
        if (format == null || format == p.getFormat()) {
          photos.add(p);
        }
      }
    }
    index++;
  }
  return photos;
}

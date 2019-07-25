/** 
 * Find all entries matching the given album and photo IDs. <code>null</code> is treated as a wildcard.
 * @param albumId provides the id to match for albums to match, if not provided, it is treated as a wildcard
 * @param photoId provides the id to match for photos to match, if not provided, it is treated as a wildcard
 * @return a list of {@link AlbumEntry} matching the  given parameters
 */
@Finder("search") @ServiceErrors(INVALID_PERMISSIONS) @ParamError(code=INVALID_ALBUM_ID,parameterNames={"albumId"}) public List<AlbumEntry> search(@Optional @QueryParam("albumId") Long albumId,@Optional @QueryParam("photoId") Long photoId){
  List<AlbumEntry> result=new ArrayList<AlbumEntry>();
  for (  Map.Entry<CompoundKey,AlbumEntry> entry : _db.getData().entrySet()) {
    CompoundKey key=entry.getKey();
    if (albumId != null && !key.getPart("albumId").equals(albumId))     continue;
    if (photoId != null && !key.getPart("photoId").equals(photoId))     continue;
    result.add(entry.getValue());
  }
  return result;
}

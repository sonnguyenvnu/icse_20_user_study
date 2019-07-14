@Override protected DataSource<Bitmap> getDataSourceForRequest(final DraweeController controller,final String controllerId,final Uri imageRequest,final Object callerContext,final CacheLevel cacheLevel){
  return new VolleyDataSource(mImageLoader,imageRequest,cacheLevel);
}

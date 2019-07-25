public void setup(final String dataset,String type,boolean immutable) throws Exception {
  if (ROARING_ONLY.equals(System.getProperty(BITMAP_TYPES)) && !ROARING.equals(type) && !ROARING_WITH_RUN.equals(type)) {
    throw new RuntimeException(String.format("Skipping non Roaring type %s",type));
  }
  bitmaps=new ArrayList<Bitmap>();
  List<int[]> ints=DATASET_CACHE.get(dataset,new Callable<List<int[]>>(){
    @Override public List<int[]> call() throws Exception {
      System.out.println("Loading" + dataset);
      ZipRealDataRetriever dataRetriever=new ZipRealDataRetriever(dataset);
      return Lists.newArrayList(dataRetriever.fetchBitPositions());
    }
  }
);
  for (  int[] data : ints) {
    Bitmap bitmap=null;
    if (CONCISE.equals(type)) {
      if (!immutable) {
        bitmap=newConciseBitmap(data);
      }
 else {
        bitmap=newImmutableConciseBitmap(data);
      }
    }
 else     if (WAH.equals(type)) {
      if (!immutable) {
        bitmap=newWahBitmap(data);
      }
 else {
        bitmap=newImmutableWahBitmap(data);
      }
    }
 else     if (EWAH.equals(type)) {
      if (!immutable) {
        bitmap=newEwahBitmap(data);
      }
 else {
        bitmap=newImmutableEwahBitmap(data);
      }
    }
 else     if (EWAH32.equals(type)) {
      if (!immutable) {
        bitmap=newEwah32Bitmap(data);
      }
 else {
        bitmap=newImmutableEwah32Bitmap(data);
      }
    }
 else     if (ROARING.equals(type)) {
      if (!immutable) {
        bitmap=newRoaringBitmap(data);
      }
 else {
        bitmap=newImmutableRoaringBitmap(data);
      }
    }
 else     if (ROARING_WITH_RUN.equals(type)) {
      if (!immutable) {
        bitmap=newRoaringWithRunBitmap(data);
      }
 else {
        bitmap=newImmutableRoaringWithRunBitmap(data);
      }
    }
    if (bitmap == null) {
      throw new RuntimeException(String.format("Unsupported parameters: type=%s, immutable=%b",type,immutable));
    }
    bitmaps.add(bitmap);
  }
}

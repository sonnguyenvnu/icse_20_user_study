@Setup(Level.Trial) public void setup() throws Exception {
  bitmaps=DATASET_CACHE.get(dataset,() -> {
    System.out.println("Loading" + dataset);
    ZipRealDataRetriever dataRetriever=new ZipRealDataRetriever(dataset);
    return StreamSupport.stream(dataRetriever.fetchBitPositions().spliterator(),false).map(RoaringBitmap::bitmapOf).toArray(RoaringBitmap[]::new);
  }
);
  immutableRoaringBitmaps=Arrays.stream(bitmaps).map(RoaringBitmap::toMutableRoaringBitmap).toArray(ImmutableRoaringBitmap[]::new);
}

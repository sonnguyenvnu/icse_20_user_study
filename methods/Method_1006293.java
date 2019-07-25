@Setup public void setup() throws ExecutionException {
  ac1=new ArrayContainer();
  mac1=new MappeableArrayContainer();
  DATASET_CACHE=CacheBuilder.newBuilder().maximumSize(1).build();
  ints=DATASET_CACHE.get(dataset,new Callable<List<int[][]>>(){
    @Override public List<int[][]> call() throws Exception {
      System.out.println("Loading" + dataset);
      ZipRealDataRangeRetriever<int[][]> dataRetriever=new ZipRealDataRangeRetriever<>(dataset,"/random-generated-data/");
      return Lists.newArrayList(dataRetriever.fetchNextRange());
    }
  }
);
}

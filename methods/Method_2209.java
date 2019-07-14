private static SparseArray<Integer> emptyHistogram(){
  SparseArray<Integer> histogram=new SparseArray<>();
  histogram.put(0,0);
  histogram.put(5,0);
  histogram.put(10,0);
  histogram.put(20,0);
  histogram.put(50,0);
  histogram.put(100,0);
  histogram.put(200,0);
  histogram.put(512,0);
  histogram.put(1024,0);
  return histogram;
}

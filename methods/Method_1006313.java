@Benchmark public int iterate(RealDataBenchmarkState bs){
  int total=0;
  for (int k=0; k < bs.bitmaps.size(); ++k) {
    Bitmap bitmap=bs.bitmaps.get(k);
    BitmapIterator i=bitmap.iterator();
    while (i.hasNext()) {
      total+=i.next();
    }
  }
  return total;
}

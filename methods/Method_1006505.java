static ArrayList<Long> buffertimings(List<ImmutableRoaringBitmap> bitmaps,int maxvalue){
  long successive_and=0;
  long successive_or=0;
  long total_or=0;
  long start, stop;
  ArrayList<Long> timings=new ArrayList<Long>();
  start=System.nanoTime();
  for (int i=0; i < bitmaps.size() - 1; ++i) {
    successive_and+=ImmutableRoaringBitmap.and(bitmaps.get(i),bitmaps.get(i + 1)).getCardinality();
  }
  if (successive_and == 0xFFFFFFFF)   System.out.println();
  stop=System.nanoTime();
  timings.add(stop - start);
  start=System.nanoTime();
  for (int i=0; i < bitmaps.size() - 1; ++i) {
    successive_or+=ImmutableRoaringBitmap.or(bitmaps.get(i),bitmaps.get(i + 1)).getCardinality();
  }
  if (successive_or == 0xFFFFFFFF)   System.out.println();
  stop=System.nanoTime();
  timings.add(stop - start);
  start=System.nanoTime();
  total_or=ImmutableRoaringBitmap.or(bitmaps.iterator()).getCardinality();
  if (total_or == 0xFFFFFFFF)   System.out.println();
  stop=System.nanoTime();
  timings.add(stop - start);
  int quartcount=0;
  start=System.nanoTime();
  for (  ImmutableRoaringBitmap rb : bitmaps) {
    if (rb.contains(maxvalue / 4))     ++quartcount;
    if (rb.contains(maxvalue / 2))     ++quartcount;
    if (rb.contains(3 * maxvalue / 4))     ++quartcount;
  }
  if (quartcount == 0xFFFFFFFF)   System.out.println();
  stop=System.nanoTime();
  timings.add(stop - start);
  return timings;
}

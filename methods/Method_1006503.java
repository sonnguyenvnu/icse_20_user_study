public static void benchmark(ZipRealDataRetriever zip) throws IOException, URISyntaxException {
  int maxvalue=universeSize(zip);
  System.out.print(String.format("%1$-25s",zip.getName()) + "\t\t");
  List<RoaringBitmap> bitmaps=toBitmaps(zip);
  display(bitmaps,maxvalue);
  System.out.print("\t");
  List<ImmutableRoaringBitmap> bufferbitmaps=toBufferBitmaps(zip);
  bufferdisplay(bufferbitmaps,maxvalue);
  System.out.println();
}

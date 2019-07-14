private static List<Data> generateData(final SectionContext c,int count){
  ImageUriProvider uris=ShowcaseApplication.Companion.getImageUriProvider();
  ArrayList<Data> data=new ArrayList<>(count);
  for (int i=1; i <= count; i++) {
    data.add(new Data(uris.createSampleUri(ImageUriProvider.ImageSize.S),uris.createSampleUri(ImageUriProvider.ImageSize.M),"Photo " + i));
  }
  return data;
}

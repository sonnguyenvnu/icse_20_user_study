public Component createComponent(ComponentContext c){
  Uri uri0=Uri.parse("http://sample.com/invalid");
  Uri uri1=sampleUris().createSampleUri(ImageUriProvider.ImageSize.XXL);
  Uri uri2=Uri.parse("http://sample.com/invalid");
  return FrescoVitoImage.create(c).multiUri(MultiUri.create().setLowResImageRequest(ImageRequest.fromUri(uri0)).setMultiImageRequests(ImageRequest.fromUri(uri0),ImageRequest.fromUri(uri1),ImageRequest.fromUri(uri2)).build()).imageOptions(IMAGE_OPTIONS).build();
}

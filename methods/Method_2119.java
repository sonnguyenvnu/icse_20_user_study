@OnCreateChildren static Children onCreateChildren(final SectionContext c){
  List<Uri> data=ShowcaseApplication.Companion.getImageUriProvider().getRandomSampleUris(ImageUriProvider.ImageSize.M,500);
  return Children.create().child(DataDiffSection.<Uri>create(c).data(data).renderEventHandler(SimpleListSection.onRender(c))).build();
}

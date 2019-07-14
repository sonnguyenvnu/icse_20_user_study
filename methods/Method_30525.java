@SuppressWarnings("deprecation") public SizedImage toFrodoSizedImage(){
  SizedImage sizedImage=new SizedImage();
  sizedImage.raw=new SizedImage.Item();
  sizedImage.raw.url=getLargeUrl();
  sizedImage.raw.width=getLargeWidth();
  sizedImage.raw.height=getLargeHeight();
  sizedImage.large=new SizedImage.Item();
  sizedImage.large.url=getLargeUrl();
  sizedImage.large.width=getLargeWidth();
  sizedImage.large.height=getLargeHeight();
  sizedImage.medium=new SizedImage.Item();
  sizedImage.medium.url=getMediumUrl();
  sizedImage.medium.width=getMediumWidth();
  sizedImage.medium.height=getMediumHeight();
  sizedImage.small=new SizedImage.Item();
  sizedImage.small.url=getSmallUrl();
  sizedImage.small.width=getSmallWidth();
  sizedImage.small.height=getSmallHeight();
  sizedImage.isAnimated=isAnimated;
  return sizedImage;
}

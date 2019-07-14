private void showImage(Post article){
  Image nestedImage=article.nestedThumbnail().get();
  Image image=ImmutableImage.builder().height(nestedImage.height()).width(nestedImage.width()).url(nestedImage.url()).build();
  BitmapTransform bitmapTransform=new BitmapTransform(maxWidth,maxHeight,image);
  int targetWidth=bitmapTransform.targetWidth;
  int targetHeight=bitmapTransform.targetHeight;
  setSpacer(targetWidth,targetHeight);
  setupThumbnail(targetWidth,targetHeight);
  Picasso.with(itemView.getContext()).load(image.url()).transform(bitmapTransform).resize(targetWidth,targetHeight).centerInside().placeholder(R.color.gray80).into(thumbnail);
}

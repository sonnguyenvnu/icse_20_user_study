private File roadImageView(Uri uri,ImageView imageView){
  RequestOptions options=new RequestOptions().placeholder(R.drawable.circle_elves_ball).error(R.drawable.circle_elves_ball).transform(new CircleCrop()).diskCacheStrategy(DiskCacheStrategy.RESOURCE);
  Glide.with(mContext).load(uri).apply(options).thumbnail(0.5f).into(imageView);
  return (new File(RxPhotoTool.getImageAbsolutePath(this,uri)));
}

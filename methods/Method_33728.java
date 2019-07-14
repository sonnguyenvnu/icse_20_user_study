/** 
 * ????????
 */
@BindingAdapter({"android:imageUrl","android:imageWidthDp","android:imageHeightDp"}) public static void imageUrl(ImageView imageView,String url,int imageWidthDp,int imageHeightDp){
  Glide.with(imageView.getContext()).load(url).override(DensityUtil.dip2px(imageWidthDp),DensityUtil.dip2px(imageHeightDp)).transition(DrawableTransitionOptions.withCrossFade(500)).placeholder(getMusicDefaultPic(4)).centerCrop().error(getDefaultPic(0)).into(imageView);
}

/** 
 * ??????
 */
@BindingAdapter("android:showBookImg") public static void showBookImg(ImageView imageView,String url){
  Glide.with(imageView.getContext()).load(url).transition(DrawableTransitionOptions.withCrossFade(500)).override((int)CommonUtils.getDimens(R.dimen.book_detail_width),(int)CommonUtils.getDimens(R.dimen.book_detail_height)).placeholder(getDefaultPic(2)).error(getDefaultPic(2)).into(imageView);
}

/** 
 * ??????
 */
@BindingAdapter("android:showMovieImg") public static void showMovieImg(ImageView imageView,String url){
  Glide.with(imageView.getContext()).load(url).transition(DrawableTransitionOptions.withCrossFade(500)).override((int)CommonUtils.getDimens(R.dimen.movie_detail_width),(int)CommonUtils.getDimens(R.dimen.movie_detail_height)).placeholder(getDefaultPic(0)).error(getDefaultPic(0)).into(imageView);
}

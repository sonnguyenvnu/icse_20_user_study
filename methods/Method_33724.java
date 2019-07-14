/** 
 * ?????,????????
 */
@BindingAdapter("android:displayCircle") public static void displayCircle(ImageView imageView,String imageUrl){
  Glide.with(imageView.getContext()).load(imageUrl).transition(DrawableTransitionOptions.withCrossFade(500)).error(R.drawable.ic_avatar_default).transform(new CircleCrop()).into(imageView);
}

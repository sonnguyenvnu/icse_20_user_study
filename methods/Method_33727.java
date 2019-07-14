/** 
 * ????????
 */
@BindingAdapter({"android:displayRandom","android:imgType"}) public static void displayRandom(ImageView imageView,int imageUrl,int imgType){
  Glide.with(imageView.getContext()).load(imageUrl).placeholder(getMusicDefaultPic(imgType)).error(getMusicDefaultPic(imgType)).transition(DrawableTransitionOptions.withCrossFade(1500)).into(imageView);
}

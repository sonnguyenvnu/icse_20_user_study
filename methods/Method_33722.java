/** 
 * ????item??gif???????
 */
public static void displayGif(String url,ImageView imageView){
  Glide.with(imageView.getContext()).asBitmap().load(url).placeholder(R.drawable.shape_bg_loading).error(R.drawable.shape_bg_loading).into(imageView);
}

/** 
 * ???????(????)
 * @param imgNumber ????????,?????
 * @param imageUrl  ?????url
 * @param imageView ??????
 */
public static void displayRandom(int imgNumber,String imageUrl,ImageView imageView){
  Glide.with(imageView.getContext()).load(imageUrl).placeholder(getMusicDefaultPic(imgNumber)).error(getMusicDefaultPic(imgNumber)).transition(DrawableTransitionOptions.withCrossFade(1500)).into(imageView);
}

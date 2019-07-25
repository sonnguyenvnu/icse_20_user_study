/** 
 * Scale an image. Cannot be used for window icons.
 * @param source Original image.
 * @param size New dimensions <i>(both width / height)</i>
 * @return Scaled image.
 */
public static Image scale(Image source,int size){
  ImageView imageView=new ImageView(source);
  imageView.setPreserveRatio(true);
  imageView.setFitWidth(size);
  imageView.setFitHeight(size);
  return imageView.snapshot(null,null);
}

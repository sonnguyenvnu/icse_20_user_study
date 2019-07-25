/** 
 * @return A copy of this image. The buffer is copied and the metadata recreated.
 */
public Image copy(){
  return new Image(bytes,imageInfo,true);
}

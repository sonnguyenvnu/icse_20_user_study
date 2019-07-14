/** 
 * resize the svg to certain width while keeping the width/height ratio
 * @param width in pixel
 */
public void setSizeForWidth(double width){
  double height=width / widthHeightRatio;
  setSize(width,height);
}

/** 
 * Adjusts current scale and translate values to keep scale within the allowed range and the image on screen. Minimum scale is set so one dimension fills the view and the image is centered on the other dimension.
 * @param center Whether the image should be centered in the dimension it's too small to fill. While animating this can be false to avoid changes in direction as bounds are reached.
 */
private void fitToBounds(boolean center){
  boolean init=false;
  if (vTranslate == null) {
    init=true;
    vTranslate=new PointF(0,0);
  }
  if (satTemp == null) {
    satTemp=new ScaleAndTranslate(0,new PointF(0,0));
  }
  satTemp.scale=scale;
  satTemp.vTranslate.set(vTranslate);
  fitToBounds(center,satTemp);
  scale=satTemp.scale;
  vTranslate.set(satTemp.vTranslate);
  if (init) {
    vTranslate.set(vTranslateForSCenter(sWidth() / 2,sHeight() / 2,scale));
  }
}

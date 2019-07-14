/** 
 * ( begin auto-generated from PImage_loadPixels.xml ) Loads the pixel data for the image into its <b>pixels[]</b> array. This function must always be called before reading from or writing to <b>pixels[]</b>. <br/><br/> renderers may or may not seem to require <b>loadPixels()</b> or <b>updatePixels()</b>. However, the rule is that any time you want to manipulate the <b>pixels[]</b> array, you must first call <b>loadPixels()</b>, and after changes have been made, call <b>updatePixels()</b>. Even if the renderer may not seem to use this function in the current Processing release, this will always be subject to change. ( end auto-generated ) <h3>Advanced</h3> Call this when you want to mess with the pixels[] array. <p/> For subclasses where the pixels[] buffer isn't set by default, this should copy all data into the pixels[] array
 * @webref pimage:pixels
 * @brief Loads the pixel data for the image into its pixels[] array
 * @usage web_application
 */
public void loadPixels(){
  if (pixels == null || pixels.length != pixelWidth * pixelHeight) {
    pixels=new int[pixelWidth * pixelHeight];
  }
  setLoaded();
}

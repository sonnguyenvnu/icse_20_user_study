/** 
 * ( begin auto-generated from PImage_mask.xml ) Masks part of an image from displaying by loading another image and using it as an alpha channel. This mask image should only contain grayscale data, but only the blue color channel is used. The mask image needs to be the same size as the image to which it is applied.<br /> <br /> In addition to using a mask image, an integer array containing the alpha channel data can be specified directly. This method is useful for creating dynamically generated alpha masks. This array must be of the same length as the target image's pixels array and should contain only grayscale data of values between 0-255. ( end auto-generated ) <h3>Advanced</h3> Set alpha channel for an image. Black colors in the source image will make the destination image completely transparent, and white will make things fully opaque. Gray values will be in-between steps. <P> Strictly speaking the "blue" value from the source image is used as the alpha color. For a fully grayscale image, this is correct, but for a color image it's not 100% accurate. For a more accurate conversion, first use filter(GRAY) which will make the image into a "correct" grayscale by performing a proper luminance-based conversion.
 * @webref pimage:method
 * @usage web_application
 * @param img image to use as the mask
 * @brief Masks part of an image with another image as an alpha channel
 */
public void mask(PImage img){
  img.loadPixels();
  mask(img.pixels);
}

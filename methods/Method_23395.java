/** 
 * Duplicate an image, returns new PImage object. The pixels[] array for the new object will be unique and recopied from the source image. This is implemented as an override of Object.clone(). We recommend using get() instead, because it prevents you from needing to catch the CloneNotSupportedException, and from doing a cast from the result.
 */
@Override public Object clone() throws CloneNotSupportedException {
  return get();
}

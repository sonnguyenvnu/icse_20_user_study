/** 
 * Set the high bits of all pixels to opaque. 
 */
protected void opaque(){
  for (int i=0; i < pixels.length; i++) {
    pixels[i]=0xFF000000 | pixels[i];
  }
}

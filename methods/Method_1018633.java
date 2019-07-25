/** 
 * Interprets a 2D array of booleans as a  {@code BitMatrix}, where "true" means an "on" bit.
 * @param image bits of the image, as a row-major 2D array. Elements are arrays representing rows
 * @return {@code BitMatrix} representation of image
 */
public static BitMatrix parse(boolean[][] image){
  int height=image.length;
  int width=image[0].length;
  BitMatrix bits=new BitMatrix(width,height);
  for (int i=0; i < height; i++) {
    boolean[] imageI=image[i];
    for (int j=0; j < width; j++) {
      if (imageI[j]) {
        bits.set(j,i);
      }
    }
  }
  return bits;
}

/** 
 * Checks whether two envelopes intersect with each other.
 * @param envelope1 first envelope, or null
 * @param envelope2 second envelope, or null
 * @return whether the specified envelopes intersects
 */
public static boolean intersects(double[] envelope1,double[] envelope2){
  return envelope1 != null && envelope2 != null && envelope1[MAX_X] >= envelope2[MIN_X] && envelope1[MIN_X] <= envelope2[MAX_X] && envelope1[MAX_Y] >= envelope2[MIN_Y] && envelope1[MIN_Y] <= envelope2[MAX_Y];
}

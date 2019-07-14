/** 
 * Checks if two  {@link File}s point to the same  {@link File}.
 * @param one {@link File} one.
 * @param two {@link File} two.
 * @return {@code true} if the {@link File}s match.
 */
public static boolean equals(File one,File two){
  try {
    one=one.getCanonicalFile();
    two=two.getCanonicalFile();
  }
 catch (  IOException ignore) {
    return false;
  }
  return one.equals(two);
}

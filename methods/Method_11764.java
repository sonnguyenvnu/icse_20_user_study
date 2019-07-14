/** 
 * The inverse of  {@link #assumeTrue(boolean)}.
 */
public static void assumeFalse(boolean b){
  assumeThat(b,is(false));
}

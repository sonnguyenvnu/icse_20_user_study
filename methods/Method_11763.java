/** 
 * If called with an expression evaluating to  {@code false}, the test will halt and be ignored.
 */
public static void assumeTrue(boolean b){
  assumeThat(b,is(true));
}

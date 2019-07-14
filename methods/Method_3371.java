/** 
 * Cleanup of registered ValueCreators (mainly for tests)
 */
public static void resetValueCreators(){
  valueCreators.clear();
  valueCreators.addAll(DEFAULT_VALUE_CREATORS);
}

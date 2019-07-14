/** 
 * @return a comparator that ranks tests based on the JUnit Max sortingrules, as described in the  {@link MaxCore} class comment.
 */
public Comparator<Description> testComparator(){
  return new TestComparator();
}

/** 
 * @hide 
 */
@Override protected void call(Param param) throws Throwable {
  if (param instanceof LayoutInflatedParam)   handleLayoutInflated((LayoutInflatedParam)param);
}

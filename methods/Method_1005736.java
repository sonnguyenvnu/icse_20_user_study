/** 
 * @hide 
 */
@Override protected void call(Param param) throws Throwable {
  if (param instanceof LoadPackageParam)   handleLoadPackage((LoadPackageParam)param);
}

/** 
 * @hide
 */
@Override protected void call(Param param) throws Throwable {
  if (param instanceof StartupParam)   initZygote((StartupParam)param);
}

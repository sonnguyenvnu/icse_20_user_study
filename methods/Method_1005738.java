/** 
 * Like  {@link #ensure(File,String,String)}, just for the default dex file name. 
 */
public static File ensure(String childClz,Class<?> superClz) throws IOException {
  return ensure(getDefaultFile(childClz),childClz,superClz.getName());
}

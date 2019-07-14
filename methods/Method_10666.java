/** 
 * ??App????
 * @param context  ???
 * @param dirPaths ????
 * @return {@code true}: ??<br> {@code false}: ??
 */
public static boolean cleanAppData(Context context,String... dirPaths){
  File[] dirs=new File[dirPaths.length];
  int i=0;
  for (  String dirPath : dirPaths) {
    dirs[i++]=new File(dirPath);
  }
  return cleanAppData(context,dirs);
}

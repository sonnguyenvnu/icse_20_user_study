/** 
 * Get a temp file.
 * @param file The base file for which to create a temp file.
 * @return The temp file.
 */
public static File getTempFile(@NonNull final File file,Context context){
  File extDir=context.getExternalFilesDir(null);
  return new File(extDir,file.getName());
}

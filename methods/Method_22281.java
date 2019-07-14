/** 
 * Closes a Closeable.
 * @param closeable Closeable to close. If closeable is null then method just returns.
 */
public static void safeClose(@Nullable Closeable closeable){
  if (closeable == null)   return;
  try {
    closeable.close();
  }
 catch (  IOException ignored) {
  }
}

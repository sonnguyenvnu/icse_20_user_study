/** 
 * @see StorageVolume#getState()
 */
@NonNull @RequiresApi(Build.VERSION_CODES.KITKAT_WATCH) @SuppressLint("NewApi") public static String getState(@NonNull StorageVolume storageVolume){
  return storageVolume.getState();
}

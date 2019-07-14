/** 
 * @see StorageManager#getStorageVolumes()
 */
@NonNull public static List<StorageVolume> getStorageVolumes(@NonNull StorageManager storageManager){
  if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
    return storageManager.getStorageVolumes();
  }
 else {
    StorageVolume[] storageVolumes=sGetVolumeListMethod.invoke(storageManager);
    return Arrays.asList(storageVolumes);
  }
}

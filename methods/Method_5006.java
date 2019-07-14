/** 
 * Converts a  {@link DownloadAction} to {@link DownloadState} and stored in the given {@link DownloadIndex}. <p>This method shouldn't be called while  {@link DownloadIndex} is used by {@link DownloadManager}.
 * @param downloadIndex The action is converted to {@link DownloadState} and stored in this index.
 * @param id A nullable custom download id which overwrites {@link DownloadAction#id}.
 * @param action The action to be stored in {@link DownloadIndex}.
 */
public static void addAction(DownloadIndex downloadIndex,@Nullable String id,DownloadAction action){
  DownloadState downloadState=downloadIndex.getDownloadState(id != null ? id : action.id);
  if (downloadState != null) {
    downloadState=merge(downloadState,action);
  }
 else {
    downloadState=convert(action);
  }
  downloadIndex.putDownloadState(downloadState);
}

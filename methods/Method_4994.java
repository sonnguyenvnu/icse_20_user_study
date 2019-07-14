/** 
 * Merge  {@link DownloadAction}s in  {@code actionQueue} to minimum number of actions.<p>All actions must have the same type and must be for the same media.
 * @param actionQueue Queue of actions. Must not be empty.
 * @return The first action in the queue.
 */
public static DownloadAction mergeActions(ArrayDeque<DownloadAction> actionQueue){
  DownloadAction removeAction=null;
  DownloadAction downloadAction=null;
  HashSet<StreamKey> keys=new HashSet<>();
  boolean downloadAllTracks=false;
  DownloadAction firstAction=Assertions.checkNotNull(actionQueue.peek());
  while (!actionQueue.isEmpty()) {
    DownloadAction action=actionQueue.remove();
    Assertions.checkState(action.type.equals(firstAction.type));
    Assertions.checkState(action.isSameMedia(firstAction));
    if (action.isRemoveAction) {
      removeAction=action;
      downloadAction=null;
      keys.clear();
      downloadAllTracks=false;
    }
 else {
      if (!downloadAllTracks) {
        if (action.keys.isEmpty()) {
          downloadAllTracks=true;
          keys.clear();
        }
 else {
          keys.addAll(action.keys);
        }
      }
      downloadAction=action;
    }
  }
  if (removeAction != null) {
    actionQueue.add(removeAction);
  }
  if (downloadAction != null) {
    actionQueue.add(DownloadAction.createDownloadAction(downloadAction.type,downloadAction.uri,new ArrayList<>(keys),downloadAction.customCacheKey,downloadAction.data));
  }
  return Assertions.checkNotNull(actionQueue.peek());
}

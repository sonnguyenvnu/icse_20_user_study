/** 
 * Upgrades an  {@link ActionFile} to {@link DownloadIndex}. <p>This method shouldn't be called while  {@link DownloadIndex} is used by {@link DownloadManager}.
 * @param actionFile The action file to upgrade.
 * @param downloadIndex Actions are converted to {@link DownloadState}s and stored in this index.
 * @param downloadIdProvider A nullable custom download id provider.
 * @throws IOException If there is an error during loading actions.
 */
public static void upgradeActionFile(ActionFile actionFile,DownloadIndex downloadIndex,@Nullable DownloadIdProvider downloadIdProvider) throws IOException {
  if (downloadIdProvider == null) {
    downloadIdProvider=downloadAction -> downloadAction.id;
  }
  for (  DownloadAction action : actionFile.load()) {
    addAction(downloadIndex,downloadIdProvider.getId(action),action);
  }
}

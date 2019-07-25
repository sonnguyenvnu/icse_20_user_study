/** 
 * Disable manager. 
 */
private void disable(){
  ExternalIndexableSetContributor.invalidateCache(project);
  virtualFileManager.removeVirtualFileListener(virtualFileListener);
  settings.removeListener(settingsListener);
  if (messageBus != null) {
    messageBus.disconnect();
    messageBus=null;
  }
  working=false;
}

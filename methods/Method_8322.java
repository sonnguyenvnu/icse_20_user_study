public boolean allowGroupPhotos(){
  return !isEditingMessageMedia() && (currentEncryptedChat == null || AndroidUtilities.getPeerLayerVersion(currentEncryptedChat.layer) >= 73);
}

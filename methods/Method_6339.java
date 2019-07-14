public boolean canAddStickerToFavorites(){
  return !stickersLoaded[0] || stickerSets[0].size() >= 5 || !recentStickers[TYPE_FAVE].isEmpty();
}

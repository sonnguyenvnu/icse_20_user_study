public boolean hasRecentGif(TLRPC.Document document){
  for (int a=0; a < recentGifs.size(); a++) {
    TLRPC.Document image=recentGifs.get(a);
    if (image.id == document.id) {
      recentGifs.remove(a);
      recentGifs.add(0,image);
      return true;
    }
  }
  return false;
}

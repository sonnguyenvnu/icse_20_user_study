private boolean merge(){
  for (int i=0; i < all.size() - 1; i++) {
    for (int j=i + 1; j < all.size(); j++) {
      if (all.get(i).doesTouch(all.get(j))) {
        all.get(i).addAll(all.get(j));
        all.remove(j);
        return true;
      }
    }
  }
  return false;
}

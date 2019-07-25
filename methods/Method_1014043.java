@SuppressWarnings("null") @Override public void removed(Item item){
  Metadata removedMd=semantics.remove(item.getName());
  if (removedMd != null) {
    notifyListenersAboutRemovedElement(removedMd);
  }
}

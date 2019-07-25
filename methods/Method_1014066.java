private void cleanup(ItemChannelLink link){
synchronized (profiles) {
    profiles.remove(link.getUID());
  }
  profileFactories.values().forEach(list -> list.remove(link.getUID()));
}

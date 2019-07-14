private void addProfiles(final String profile){
  if (propsIterator.profiles == null) {
    propsIterator.profiles=new ArrayList<>();
  }
  propsIterator.profiles.add(profile);
}

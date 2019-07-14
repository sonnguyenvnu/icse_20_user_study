private void notifyRemove(Contribution contribution){
  for (  ChangeListener listener : listeners) {
    listener.contributionRemoved(contribution);
  }
}

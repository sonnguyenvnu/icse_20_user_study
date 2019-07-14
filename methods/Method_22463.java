private void notifyAdd(Contribution contribution){
  for (  ChangeListener listener : listeners) {
    listener.contributionAdded(contribution);
  }
}

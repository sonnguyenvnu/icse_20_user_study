private void notifyChange(Contribution oldLib,Contribution newLib){
  for (  ChangeListener listener : listeners) {
    listener.contributionChanged(oldLib,newLib);
  }
}

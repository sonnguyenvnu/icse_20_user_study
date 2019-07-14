@Override public void pathSaved(Path path){
  if (((counter++) & mask) == 0) {
    saveAllSourcesView.updateProgressBar(counter);
  }
}

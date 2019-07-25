@Override public void customize(GitIgnore gitIgnore){
  if (!this.document.isEmpty()) {
    gitIgnore.getGeneral().add("HELP.md");
  }
}

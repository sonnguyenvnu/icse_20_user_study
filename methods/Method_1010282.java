public void load(Memento memento){
  mySkipPrivate=Boolean.parseBoolean(memento.get("skip-private"));
  for (  Memento entry : memento.getChildren("include")) {
    includeWithPrefix(entry.get("prefix"));
  }
  for (  Memento entry : memento.getChildren("exclude")) {
    excludeWithPrefix(entry.get("prefix"));
  }
}

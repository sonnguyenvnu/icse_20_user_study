@Override public void merge(List<DefaultMetadataElement> otherContent){
  otherContent.forEach((it) -> {
    if (get(it.getId()) == null) {
      this.content.add(it);
    }
  }
);
}

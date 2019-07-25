@Override public void merge(List<Type> otherContent){
  otherContent.forEach((it) -> {
    if (get(it.getId()) == null) {
      this.content.add(it);
    }
  }
);
}

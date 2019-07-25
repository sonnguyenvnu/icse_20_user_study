@Override public void merge(List<DependencyGroup> otherContent){
  otherContent.forEach((group) -> {
    if (this.content.stream().noneMatch((it) -> group.getName() != null && group.getName().equals(it.getName()))) {
      this.content.add(group);
    }
  }
);
  index();
}

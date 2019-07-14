protected int sectionCount(final int section){
  if (section > sections().size() - 1) {
    return 0;
  }
  return sections().get(section).size();
}

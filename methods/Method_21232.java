private @NonNull SectionRow sectionRowFromPosition(final int position){
  final SectionRow sectionRow=new SectionRow();
  int cursor=0;
  for (  final List<?> section : this.sections) {
    for (    final Object __ : section) {
      if (cursor == position) {
        return sectionRow;
      }
      cursor++;
      sectionRow.nextRow();
    }
    sectionRow.nextSection();
  }
  throw new RuntimeException("Position " + position + " not found in sections");
}

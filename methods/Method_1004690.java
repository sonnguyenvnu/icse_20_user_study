public void write(PrintWriter writer) throws IOException {
  LinkedList<Section> allSections=new LinkedList<>(this.sections);
  allSections.addFirst(this.gettingStarted);
  allSections.addLast(this.nextSteps);
  for (  Section section : allSections) {
    section.write(writer);
  }
}

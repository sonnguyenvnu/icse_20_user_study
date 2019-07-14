private void addOutline(List<Outliner.Entry> entries){
  add("<ul>\n");
  for (  Outliner.Entry e : entries) {
    addEntry(e);
  }
  add("</ul>\n");
}

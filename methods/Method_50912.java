private String setLabelFor(Match match){
  Set<String> sourceIDs=new HashSet<>(match.getMarkCount());
  for (Iterator<Mark> occurrences=match.iterator(); occurrences.hasNext(); ) {
    sourceIDs.add(occurrences.next().getFilename());
  }
  String label;
  if (sourceIDs.size() == 1) {
    String sourceId=sourceIDs.iterator().next();
    int separatorPos=sourceId.lastIndexOf(File.separatorChar);
    label="..." + sourceId.substring(separatorPos);
  }
 else {
    label=String.format("(%d separate files)",sourceIDs.size());
  }
  match.setLabel(label);
  return label;
}

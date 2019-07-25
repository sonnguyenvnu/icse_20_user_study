private void append(List<String> result,LineLocation lineLocation){
  if (lineLocation.getDescription() != null) {
    result.add("[From " + lineLocation.getDescription() + " (line " + (lineLocation.getPosition() + 1) + ") ]");
  }
}

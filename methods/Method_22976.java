public void updateErrorPoints(final List<Problem> problems){
  errorPoints=problems.stream().map(LineMarker::new).collect(Collectors.toList());
  repaint();
}

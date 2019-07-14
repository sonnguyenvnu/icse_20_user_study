public void setProblemList(List<Problem> problems){
  this.problems=problems;
  boolean hasErrors=problems.stream().anyMatch(Problem::isError);
  updateErrorTable(problems);
  errorColumn.updateErrorPoints(problems);
  textarea.repaint();
  updateErrorToggle(hasErrors);
  updateEditorStatus();
}

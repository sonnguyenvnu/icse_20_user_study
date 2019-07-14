double totalAssignmentCost(){
  return assignmentCost().stream().mapToDouble(d -> d).sum();
}

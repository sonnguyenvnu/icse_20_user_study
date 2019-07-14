public Assignments assignNext(PotentialAssignment source){
  List<PotentialAssignment> potentialAssignments=new ArrayList<PotentialAssignment>(assigned);
  potentialAssignments.add(source);
  return new Assignments(potentialAssignments,unassigned.subList(1,unassigned.size()),clazz);
}

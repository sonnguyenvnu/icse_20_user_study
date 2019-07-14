public void flushJoinsInvolvingProject(long projectID){
synchronized (_joins) {
    for (Iterator<Entry<String,ProjectJoin>> it=_joins.entrySet().iterator(); it.hasNext(); ) {
      Entry<String,ProjectJoin> entry=it.next();
      ProjectJoin join=entry.getValue();
      if (join.fromProjectID == projectID || join.toProjectID == projectID) {
        it.remove();
      }
    }
  }
}

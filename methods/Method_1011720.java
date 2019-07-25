private ProjectMigration next(ProjectMigration current,final boolean cleanup){
  List<ProjectMigration> mig=ProjectMigrationsRegistry.getInstance().getMigrations();
  mig=ListSequence.fromList(mig).where(new IWhereFilter<ProjectMigration>(){
    public boolean accept(    ProjectMigration it){
      boolean isCleanup=it instanceof CleanupProjectMigration;
      return (cleanup ? isCleanup : !(isCleanup));
    }
  }
).toListSequence();
  if (ListSequence.fromList(mig).isEmpty()) {
    return null;
  }
  if (ListSequence.fromList(mig).indexOf(current) < 0) {
    current=null;
  }
  if (current == null) {
    return ListSequence.fromList(mig).getElement(0);
  }
  int index=ListSequence.fromList(mig).indexOf(current);
  if (index == ListSequence.fromList(mig).count() - 1) {
    return null;
  }
  return ListSequence.fromList(mig).getElement(index + 1);
}

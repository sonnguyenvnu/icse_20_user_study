public static List<Pair<Project,DiscoveryParams>> combineProjectsAndParams(final @NonNull List<Project> projects,final @NonNull DiscoveryParams params){
  final ArrayList<Pair<Project,DiscoveryParams>> projectAndParams=new ArrayList<>(projects.size());
  for (int i=0; i < projects.size(); i++) {
    projectAndParams.add(Pair.create(projects.get(i),params));
  }
  return projectAndParams;
}

/** 
 * Converts a pair (params, project) into a (project, refTag) pair that does some extra logic around featured projects.
 */
public static @NonNull Pair<Project,RefTag> projectAndRefTagFromParamsAndProject(final @NonNull DiscoveryParams params,final @NonNull Project project){
  final RefTag refTag=project.isFeaturedToday() ? RefTag.categoryFeatured() : DiscoveryParamsUtils.refTag(params);
  return new Pair<>(project,refTag);
}

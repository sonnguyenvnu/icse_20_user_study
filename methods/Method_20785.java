/** 
 * Determines if the project and supplied require us to find the root category.
 */
public static boolean projectNeedsRootCategory(final @NonNull Project project,final @NonNull Category category){
  return !category.isRoot() && category.parent() == null && project.isFeaturedToday();
}

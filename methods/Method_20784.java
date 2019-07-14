/** 
 * Given a list of projects and root categories this will determine if the first project is featured and is in need of its root category. If that is the case we will find its root and fill in that data and return a new list of projects.
 */
public static List<Project> fillRootCategoryForFeaturedProjects(final @NonNull List<Project> projects,final @NonNull List<Category> rootCategories){
  if (projects.size() == 0) {
    return ListUtils.empty();
  }
  final Project firstProject=projects.get(0);
  final Category category=firstProject.category();
  if (category == null) {
    return projects;
  }
  final Long categoryParentId=category.parentId();
  if (categoryParentId == null) {
    return projects;
  }
  if (!projectNeedsRootCategory(firstProject,category)) {
    return projects;
  }
  final Category projectRootCategory=Observable.from(rootCategories).filter(rootCategory -> rootCategory.id() == categoryParentId).take(1).toBlocking().single();
  final Category newCategory=category.toBuilder().parent(projectRootCategory).build();
  final Project newProject=firstProject.toBuilder().category(newCategory).build();
  return ListUtils.replaced(projects,0,newProject);
}

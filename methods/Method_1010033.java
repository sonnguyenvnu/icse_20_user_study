/** 
 * Shorthand for  {@link PropertiesComponent#getInstance} method.
 * @param project current project
 * @return component instance
 */
@NotNull private static PropertiesComponent properties(@NotNull Project project){
  return PropertiesComponent.getInstance(project);
}

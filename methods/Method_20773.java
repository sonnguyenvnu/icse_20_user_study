/** 
 * Converts all the disparate data representing the state of the menu data into a `NavigationDrawerData` object that can be used to populate a view.
 * @param categories The full list of categories that can be displayed.
 * @param selected The params that correspond to what is currently selected in the menu.
 * @param expandedCategory The category that correspond to what is currently expanded in the menu.
 * @param user The currently logged in user.
 */
public static @NonNull NavigationDrawerData deriveNavigationDrawerData(final @NonNull List<Category> categories,final @NonNull DiscoveryParams selected,final @Nullable Category expandedCategory,final @Nullable User user){
  final NavigationDrawerData.Builder builder=NavigationDrawerData.builder();
  final List<NavigationDrawerData.Section> categorySections=Observable.from(categories).filter(c -> isVisible(c,expandedCategory)).flatMap(c -> doubleRootIfExpanded(c,expandedCategory)).map(c -> DiscoveryParams.builder().category(c).build()).toList().map(DiscoveryDrawerUtils::paramsGroupedByRootCategory).map(sections -> sectionsFromAllParams(sections,expandedCategory)).toBlocking().single();
  final List<NavigationDrawerData.Section> sections=Observable.from(categorySections).startWith(topSections(user)).toList().toBlocking().single();
  return builder.sections(sections).user(user).selectedParams(selected).expandedCategory(expandedCategory).build();
}

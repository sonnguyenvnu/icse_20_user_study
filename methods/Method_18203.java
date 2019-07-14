/** 
 * Common dynamic props could only be bound to Views. To make it work for the LayoutSpec and MountDrawableSpec components we create a wrapping HostComponent and copy the dynamic props there. Thus DynamicPropsManager should ignore non-MountViewSpecs
 * @param component Component to consider
 * @return true if Component has common dynamic props, that DynamicPropsManager should take anaction on
 */
private static boolean hasCommonDynamicPropsToBind(Component component){
  return component.hasCommonDynamicProps() && Component.isMountViewSpec(component);
}

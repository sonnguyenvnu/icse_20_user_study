static boolean isNestedTree(@Nullable Component component){
  return (isLayoutSpecWithSizeSpec(component) || (component != null && component.getCachedLayout() != null));
}

@NonNull @Override public List<ViewAttribute> collect(DrawerLayout view,AttributeTranslator attributeTranslator){
  List<ViewAttribute> attributes=new ArrayList<>();
  attributes.add(new ViewAttribute<>("DrawerElevation",view.getDrawerElevation()));
  attributes.add(new ViewAttribute<>("DrawerTitleStart",view.getDrawerTitle(Gravity.START)));
  attributes.add(new ViewAttribute<>("DrawerTitleEnd",view.isDrawerVisible(Gravity.END)));
  attributes.add(new ViewAttribute<>("DrawerLockModeStart",new DrawerLayoutLockModeValue(view.getDrawerLockMode(Gravity.START))));
  attributes.add(new ViewAttribute<>("DrawerLockModeEnd",new DrawerLayoutLockModeValue(view.getDrawerLockMode(Gravity.END))));
  attributes.add(new ViewAttribute<>("DrawerOpenStart",view.isDrawerOpen(Gravity.START)));
  attributes.add(new ViewAttribute<>("DrawerOpenEnd",view.isDrawerOpen(Gravity.END)));
  attributes.add(new ViewAttribute<>("DrawerVisibleStart",view.isDrawerVisible(Gravity.START)));
  attributes.add(new ViewAttribute<>("DrawerVisibleEnd",view.isDrawerVisible(Gravity.END)));
  attributes.add(new ViewAttribute<Void>("StatusBarBackgroundDrawable",view.getStatusBarBackgroundDrawable()));
  return attributes;
}

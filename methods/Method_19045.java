@VisibleForTesting(otherwise=VisibleForTesting.PACKAGE_PRIVATE) public static SectionContext withSectionTree(SectionContext context,SectionTree sectionTree){
  SectionContext sectionContext=new SectionContext(context);
  sectionContext.mSectionTree=sectionTree;
  sectionContext.mTreeLoadingEventHandler=new SectionTreeLoadingEventHandler(sectionTree);
  return sectionContext;
}

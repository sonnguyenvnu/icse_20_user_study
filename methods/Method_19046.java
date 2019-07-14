@VisibleForTesting(otherwise=VisibleForTesting.PACKAGE_PRIVATE) public static SectionContext withScope(SectionContext context,Section scope){
  SectionContext sectionContext=new SectionContext(context);
  sectionContext.mSectionTree=context.mSectionTree;
  sectionContext.mTreeLoadingEventHandler=context.mTreeLoadingEventHandler;
  sectionContext.mScope=new WeakReference<>(scope);
  return sectionContext;
}

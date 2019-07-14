/** 
 * Store a working range information into a list for later use by  {@link LayoutState}. 
 */
protected static void registerWorkingRange(String name,WorkingRange workingRange,Component component){
  if (component.mWorkingRangeRegistrations == null) {
    component.mWorkingRangeRegistrations=new ArrayList<>();
  }
  component.mWorkingRangeRegistrations.add(new WorkingRangeContainer.Registration(name,workingRange,component));
}

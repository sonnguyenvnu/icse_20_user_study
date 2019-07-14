private void updateWith(ComponentContext c,YogaNode node,List<Component> components){
  mComponentContext=c;
  mYogaNode=node;
  mYogaNode.setData(this);
  mComponents=components;
  mComponentsNeedingPreviousRenderData=null;
  for (  Component component : components) {
    if (component.needsPreviousRenderData()) {
      if (mComponentsNeedingPreviousRenderData == null) {
        mComponentsNeedingPreviousRenderData=new ArrayList<>(1);
      }
      mComponentsNeedingPreviousRenderData.add(component);
    }
  }
  ArrayList<WorkingRangeContainer.Registration> ranges=mWorkingRangeRegistrations;
  mWorkingRangeRegistrations=null;
  if (ranges != null && !ranges.isEmpty()) {
    mWorkingRangeRegistrations=new ArrayList<>(ranges.size());
    for (    WorkingRangeContainer.Registration old : ranges) {
      final Component component=old.mComponent.makeUpdatedShallowCopy(c);
      mWorkingRangeRegistrations.add(new WorkingRangeContainer.Registration(old.mName,old.mWorkingRange,component));
    }
  }
}

public void invalidateDecorators(){
  decoratorResults=new ArrayList<>();
  for (  DayViewDecorator decorator : decorators) {
    DayViewFacade facade=new DayViewFacade();
    decorator.decorate(facade);
    if (facade.isDecorated()) {
      decoratorResults.add(new DecoratorResult(decorator,facade));
    }
  }
  for (  V pagerView : currentViews) {
    pagerView.setDayViewDecorators(decoratorResults);
  }
}

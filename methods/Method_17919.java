@ThreadSafe(enableChecks=false) private Component createComponentLayout(ComponentContext context){
  Component layoutComponent=null;
  if (Component.isLayoutSpecWithSizeSpec(((Component)this))) {
    try {
      layoutComponent=onCreateLayoutWithSizeSpec(context,context.getWidthSpec(),context.getHeightSpec());
    }
 catch (    Exception e) {
      dispatchErrorEvent(context,e);
    }
  }
 else {
    try {
      layoutComponent=onCreateLayout(context);
    }
 catch (    Exception e) {
      dispatchErrorEvent(context,e);
    }
  }
  return layoutComponent;
}

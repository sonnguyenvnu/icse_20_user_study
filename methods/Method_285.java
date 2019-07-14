private static @Nullable Unbinder parseOnPageChange(final Object target,final Method method,View source){
  OnPageChange onPageChange=method.getAnnotation(OnPageChange.class);
  if (onPageChange == null) {
    return null;
  }
  validateMember(method);
  validateReturnType(method,void.class);
  List<ViewPager> views=findViews(source,onPageChange.value(),isRequired(method),method.getName(),ViewPager.class);
  ViewPager.OnPageChangeListener listener;
switch (onPageChange.callback()) {
case PAGE_SCROLLED:
{
      ArgumentTransformer argumentTransformer=createArgumentTransformer(method,ON_PAGE_SCROLLED_TYPES);
      listener=new ViewPager.SimpleOnPageChangeListener(){
        @Override public void onPageScrolled(        int position,        float positionOffset,        int positionOffsetPixels){
          tryInvoke(method,target,argumentTransformer.transform(position,positionOffset,positionOffsetPixels));
        }
      }
;
      break;
    }
case PAGE_SELECTED:
{
    ArgumentTransformer argumentTransformer=createArgumentTransformer(method,ON_PAGE_SELECTED_TYPES);
    listener=new ViewPager.SimpleOnPageChangeListener(){
      @Override public void onPageSelected(      int position){
        tryInvoke(method,target,argumentTransformer.transform(position));
      }
    }
;
    break;
  }
case PAGE_SCROLL_STATE_CHANGED:
{
  ArgumentTransformer argumentTransformer=createArgumentTransformer(method,ON_PAGE_SCROLL_STATE_CHANGED_TYPES);
  listener=new ViewPager.SimpleOnPageChangeListener(){
    @Override public void onPageScrollStateChanged(    int state){
      tryInvoke(method,target,argumentTransformer.transform(state));
    }
  }
;
  break;
}
default :
throw new AssertionError();
}
ViewCollections.set(views,ADD_ON_PAGE_CHANGE,listener);
return new ListenerUnbinder<>(views,REMOVE_ON_PAGE_CHANGE,listener);
}

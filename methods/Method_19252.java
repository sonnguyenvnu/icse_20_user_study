/** 
 * Return the accumulated height of ComponentTreeHolders, or return the  {@param maxHeight} if theaccumulated height is higher than the  {@param maxHeight}.
 */
public static int computeLinearLayoutWrappedHeight(LinearLayoutManager linearLayoutManager,int maxHeight,List<ComponentTreeHolder> componentTreeHolders){
  final int itemCount=componentTreeHolders.size();
  int measuredHeight=0;
switch (linearLayoutManager.getOrientation()) {
case LinearLayoutManager.VERTICAL:
    for (int i=0; i < itemCount; i++) {
      final ComponentTreeHolder holder=componentTreeHolders.get(i);
      measuredHeight+=holder.getMeasuredHeight();
      measuredHeight+=LayoutInfoUtils.getTopDecorationHeight(linearLayoutManager,i);
      measuredHeight+=LayoutInfoUtils.getBottomDecorationHeight(linearLayoutManager,i);
      if (measuredHeight > maxHeight) {
        measuredHeight=maxHeight;
        break;
      }
    }
  return measuredHeight;
case LinearLayoutManager.HORIZONTAL:
default :
throw new IllegalStateException("This method should only be called when orientation is vertical");
}
}

@Override protected void onMeasure(int widthSpec,int heightSpec){
  consistencyCheck();
  invalidateValues();
  colCount=0;
  for (int a=0, N=getChildCount(); a < N; a++) {
    Child child=getChildAt(a);
    colCount=Math.max(colCount,child.layoutParams.columnSpec.span.max);
  }
  measureChildrenWithMargins(widthSpec,heightSpec,true);
  int widthSansPadding;
  int heightSansPadding;
  if (mOrientation == HORIZONTAL) {
    widthSansPadding=mHorizontalAxis.getMeasure(widthSpec);
    measureChildrenWithMargins(widthSpec,heightSpec,false);
    heightSansPadding=mVerticalAxis.getMeasure(heightSpec);
  }
 else {
    heightSansPadding=mVerticalAxis.getMeasure(heightSpec);
    measureChildrenWithMargins(widthSpec,heightSpec,false);
    widthSansPadding=mHorizontalAxis.getMeasure(widthSpec);
  }
  int measuredWidth=max(widthSansPadding,MeasureSpec.getSize(widthSpec));
  int measuredHeight=max(heightSansPadding,getSuggestedMinimumHeight());
  setMeasuredDimension(measuredWidth,measuredHeight);
  mHorizontalAxis.layout(measuredWidth);
  mVerticalAxis.layout(measuredHeight);
  int[] hLocations=mHorizontalAxis.getLocations();
  int[] vLocations=mVerticalAxis.getLocations();
  int fixedHeight=measuredHeight;
  cellsToFixHeight.clear();
  measuredWidth=hLocations[hLocations.length - 1];
  for (int i=0, N=getChildCount(); i < N; i++) {
    Child c=getChildAt(i);
    LayoutParams lp=c.getLayoutParams();
    Spec columnSpec=lp.columnSpec;
    Spec rowSpec=lp.rowSpec;
    Interval colSpan=columnSpec.span;
    Interval rowSpan=rowSpec.span;
    int x1=hLocations[colSpan.min];
    int y1=vLocations[rowSpan.min];
    int x2=hLocations[colSpan.max];
    int y2=vLocations[rowSpan.max];
    int cellWidth=x2 - x1;
    int cellHeight=y2 - y1;
    int pWidth=getMeasurement(c,true);
    int pHeight=getMeasurement(c,false);
    Alignment hAlign=columnSpec.getAbsoluteAlignment(true);
    Alignment vAlign=rowSpec.getAbsoluteAlignment(false);
    Bounds boundsX=mHorizontalAxis.getGroupBounds().getValue(i);
    Bounds boundsY=mVerticalAxis.getGroupBounds().getValue(i);
    int gravityOffsetX=hAlign.getGravityOffset(c,cellWidth - boundsX.size(true));
    int gravityOffsetY=vAlign.getGravityOffset(c,cellHeight - boundsY.size(true));
    int leftMargin=getMargin(c,true,true);
    int topMargin=getMargin(c,false,true);
    int rightMargin=getMargin(c,true,false);
    int bottomMargin=getMargin(c,false,false);
    int sumMarginsX=leftMargin + rightMargin;
    int sumMarginsY=topMargin + bottomMargin;
    int alignmentOffsetX=boundsX.getOffset(this,c,hAlign,pWidth + sumMarginsX,true);
    int alignmentOffsetY=boundsY.getOffset(this,c,vAlign,pHeight + sumMarginsY,false);
    int width=hAlign.getSizeInCell(c,pWidth,cellWidth - sumMarginsX);
    int height=vAlign.getSizeInCell(c,pHeight,cellHeight - sumMarginsY);
    int dx=x1 + gravityOffsetX + alignmentOffsetX;
    int cx=!isRtl ? leftMargin + dx : measuredWidth - width - rightMargin - dx;
    int cy=y1 + gravityOffsetY + alignmentOffsetY + topMargin;
    if (c.cell != null) {
      if (width != c.getMeasuredWidth() || height != c.getMeasuredHeight()) {
        c.measure(width,height,false);
      }
      if (c.fixedHeight != 0 && c.fixedHeight != height && c.layoutParams.rowSpec.span.max - c.layoutParams.rowSpec.span.min <= 1) {
        boolean found=false;
        for (int a=0, size=rowSpans.size(); a < size; a++) {
          Point p=rowSpans.get(a);
          if (p.x <= c.layoutParams.rowSpec.span.min && p.y > c.layoutParams.rowSpec.span.min) {
            found=true;
            break;
          }
        }
        if (!found) {
          cellsToFixHeight.add(c);
        }
      }
    }
    c.layout(cx,cy,cx + width,cy + height);
  }
  for (int a=0, N=cellsToFixHeight.size(); a < N; a++) {
    Child child=cellsToFixHeight.get(a);
    boolean skip=false;
    int heightDiff=child.measuredHeight - child.fixedHeight;
    for (int i=child.index + 1, size=childrens.size(); i < size; i++) {
      Child next=childrens.get(i);
      if (child.layoutParams.rowSpec.span.min == next.layoutParams.rowSpec.span.min) {
        if (child.fixedHeight < next.fixedHeight) {
          skip=true;
          break;
        }
 else {
          int diff=next.measuredHeight - next.fixedHeight;
          if (diff > 0) {
            heightDiff=Math.min(heightDiff,diff);
          }
        }
      }
 else {
        break;
      }
    }
    if (!skip) {
      for (int i=child.index - 1; i >= 0; i--) {
        Child next=childrens.get(i);
        if (child.layoutParams.rowSpec.span.min == next.layoutParams.rowSpec.span.min) {
          if (child.fixedHeight < next.fixedHeight) {
            skip=true;
            break;
          }
 else {
            int diff=next.measuredHeight - next.fixedHeight;
            if (diff > 0) {
              heightDiff=Math.min(heightDiff,diff);
            }
          }
        }
 else {
          break;
        }
      }
    }
    if (skip) {
      continue;
    }
    child.setFixedHeight(child.fixedHeight);
    fixedHeight-=heightDiff;
    for (int i=0, size=childrens.size(); i < size; i++) {
      Child next=childrens.get(i);
      if (child == next) {
        continue;
      }
      if (child.layoutParams.rowSpec.span.min == next.layoutParams.rowSpec.span.min) {
        if (next.fixedHeight != next.measuredHeight) {
          cellsToFixHeight.remove(next);
          if (next.index < child.index) {
            a--;
          }
          N--;
        }
        next.measuredHeight-=heightDiff;
        next.measure(next.measuredWidth,next.measuredHeight,true);
      }
 else       if (child.layoutParams.rowSpec.span.min < next.layoutParams.rowSpec.span.min) {
        next.y-=heightDiff;
      }
    }
  }
  setMeasuredDimension(measuredWidth,fixedHeight);
}

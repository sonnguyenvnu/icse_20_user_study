@Deprecated static void showOnAnchor(DeprecatedLithoTooltip tooltip,Rect anchorBounds,View hostView,TooltipPosition tooltipPosition,int xOffset,int yOffset){
  final int topOffset=anchorBounds.top - hostView.getHeight();
  final int bottomOffset=anchorBounds.bottom - hostView.getHeight();
  final int centerXOffset=anchorBounds.left + (anchorBounds.right - anchorBounds.left) / 2;
  final int centerYOffset=(anchorBounds.top + (anchorBounds.bottom - anchorBounds.top) / 2) - hostView.getHeight();
  final int xoff, yoff;
switch (tooltipPosition) {
case CENTER:
    xoff=centerXOffset;
  yoff=centerYOffset;
break;
case CENTER_LEFT:
xoff=anchorBounds.left;
yoff=centerYOffset;
break;
case TOP_LEFT:
xoff=anchorBounds.left;
yoff=topOffset;
break;
case CENTER_TOP:
xoff=centerXOffset;
yoff=topOffset;
break;
case TOP_RIGHT:
xoff=anchorBounds.right;
yoff=topOffset;
break;
case CENTER_RIGHT:
xoff=anchorBounds.right;
yoff=centerYOffset;
break;
case BOTTOM_RIGHT:
xoff=anchorBounds.right;
yoff=bottomOffset;
break;
case CENTER_BOTTOM:
xoff=centerXOffset;
yoff=bottomOffset;
break;
case BOTTOM_LEFT:
default :
xoff=anchorBounds.left;
yoff=bottomOffset;
}
tooltip.showBottomLeft(hostView,xoff + xOffset,yoff + yOffset);
}

private void handlePinch(int state,MotionEvent event){
switch (state) {
case GestureStateBegan:
{
      startPointerDistance=getDistance(event);
      pointerScale=1;
      activeControl=BlurViewActiveControl.BlurViewActiveControlWholeArea;
      setSelected(true,true);
    }
case GestureStateChanged:
{
    float newDistance=getDistance(event);
    pointerScale+=(newDistance - startPointerDistance) / AndroidUtilities.density * 0.01f;
    falloff=Math.max(BlurMinimumFalloff,falloff * pointerScale);
    size=Math.max(falloff + BlurMinimumDifference,size * pointerScale);
    pointerScale=1;
    startPointerDistance=newDistance;
    invalidate();
    if (delegate != null) {
      delegate.valueChanged(centerPoint,falloff,size,degreesToRadians(angle) + (float)Math.PI / 2.0f);
    }
  }
break;
case GestureStateEnded:
case GestureStateCancelled:
case GestureStateFailed:
{
activeControl=BlurViewActiveControl.BlurViewActiveControlNone;
setSelected(false,true);
}
break;
default :
break;
}
}

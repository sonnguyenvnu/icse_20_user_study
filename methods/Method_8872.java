private void handlePan(int state,MotionEvent event){
  float locationX=event.getX();
  float locationY=event.getY();
  Point actualCenterPoint=getActualCenterPoint();
  Point delta=new Point(locationX - actualCenterPoint.x,locationY - actualCenterPoint.y);
  float radialDistance=(float)Math.sqrt(delta.x * delta.x + delta.y * delta.y);
  float shorterSide=(actualAreaSize.width > actualAreaSize.height) ? actualAreaSize.height : actualAreaSize.width;
  float innerRadius=shorterSide * falloff;
  float outerRadius=shorterSide * size;
  float distance=(float)Math.abs(delta.x * Math.cos(degreesToRadians(angle) + Math.PI / 2.0f) + delta.y * Math.sin(degreesToRadians(angle) + Math.PI / 2.0f));
switch (state) {
case GestureStateBegan:
{
      pointerStartX=event.getX();
      pointerStartY=event.getY();
      boolean close=Math.abs(outerRadius - innerRadius) < BlurInsetProximity;
      float innerRadiusOuterInset=close ? 0 : BlurViewRadiusInset;
      float outerRadiusInnerInset=close ? 0 : BlurViewRadiusInset;
      if (type == 0) {
        if (radialDistance < BlurViewCenterInset) {
          activeControl=BlurViewActiveControl.BlurViewActiveControlCenter;
          startCenterPoint=actualCenterPoint;
        }
 else         if (distance > innerRadius - BlurViewRadiusInset && distance < innerRadius + innerRadiusOuterInset) {
          activeControl=BlurViewActiveControl.BlurViewActiveControlInnerRadius;
          startDistance=distance;
          startRadius=innerRadius;
        }
 else         if (distance > outerRadius - outerRadiusInnerInset && distance < outerRadius + BlurViewRadiusInset) {
          activeControl=BlurViewActiveControl.BlurViewActiveControlOuterRadius;
          startDistance=distance;
          startRadius=outerRadius;
        }
 else         if (distance <= innerRadius - BlurViewRadiusInset || distance >= outerRadius + BlurViewRadiusInset) {
          activeControl=BlurViewActiveControl.BlurViewActiveControlRotation;
        }
      }
 else       if (type == 1) {
        if (radialDistance < BlurViewCenterInset) {
          activeControl=BlurViewActiveControl.BlurViewActiveControlCenter;
          startCenterPoint=actualCenterPoint;
        }
 else         if (radialDistance > innerRadius - BlurViewRadiusInset && radialDistance < innerRadius + innerRadiusOuterInset) {
          activeControl=BlurViewActiveControl.BlurViewActiveControlInnerRadius;
          startDistance=radialDistance;
          startRadius=innerRadius;
        }
 else         if (radialDistance > outerRadius - outerRadiusInnerInset && radialDistance < outerRadius + BlurViewRadiusInset) {
          activeControl=BlurViewActiveControl.BlurViewActiveControlOuterRadius;
          startDistance=radialDistance;
          startRadius=outerRadius;
        }
      }
      setSelected(true,true);
    }
  break;
case GestureStateChanged:
{
  if (type == 0) {
switch (activeControl) {
case BlurViewActiveControlCenter:
{
        float translationX=locationX - pointerStartX;
        float translationY=locationY - pointerStartY;
        Rect actualArea=new Rect((getWidth() - actualAreaSize.width) / 2,(getHeight() - actualAreaSize.height) / 2,actualAreaSize.width,actualAreaSize.height);
        Point newPoint=new Point(Math.max(actualArea.x,Math.min(actualArea.x + actualArea.width,startCenterPoint.x + translationX)),Math.max(actualArea.y,Math.min(actualArea.y + actualArea.height,startCenterPoint.y + translationY)));
        centerPoint=new Point((newPoint.x - actualArea.x) / actualAreaSize.width,((newPoint.y - actualArea.y) + (actualAreaSize.width - actualAreaSize.height) / 2) / actualAreaSize.width);
      }
    break;
case BlurViewActiveControlInnerRadius:
{
    float d=distance - startDistance;
    falloff=Math.min(Math.max(BlurMinimumFalloff,(startRadius + d) / shorterSide),size - BlurMinimumDifference);
  }
break;
case BlurViewActiveControlOuterRadius:
{
float d=distance - startDistance;
size=Math.max(falloff + BlurMinimumDifference,(startRadius + d) / shorterSide);
}
break;
case BlurViewActiveControlRotation:
{
float translationX=locationX - pointerStartX;
float translationY=locationY - pointerStartY;
boolean clockwise=false;
boolean right=locationX > actualCenterPoint.x;
boolean bottom=locationY > actualCenterPoint.y;
if (!right && !bottom) {
if (Math.abs(translationY) > Math.abs(translationX)) {
if (translationY < 0) {
  clockwise=true;
}
}
 else {
if (translationX > 0) {
  clockwise=true;
}
}
}
 else if (right && !bottom) {
if (Math.abs(translationY) > Math.abs(translationX)) {
if (translationY > 0) {
  clockwise=true;
}
}
 else {
if (translationX > 0) {
  clockwise=true;
}
}
}
 else if (right && bottom) {
if (Math.abs(translationY) > Math.abs(translationX)) {
if (translationY > 0) {
  clockwise=true;
}
}
 else {
if (translationX < 0) {
  clockwise=true;
}
}
}
 else {
if (Math.abs(translationY) > Math.abs(translationX)) {
if (translationY < 0) {
  clockwise=true;
}
}
 else {
if (translationX < 0) {
  clockwise=true;
}
}
}
float d=(float)Math.sqrt(translationX * translationX + translationY * translationY);
angle+=d * ((clockwise ? 1 : 0) * 2 - 1) / (float)Math.PI / 1.15f;
pointerStartX=locationX;
pointerStartY=locationY;
}
break;
default :
break;
}
}
 else if (type == 1) {
switch (activeControl) {
case BlurViewActiveControlCenter:
{
float translationX=locationX - pointerStartX;
float translationY=locationY - pointerStartY;
Rect actualArea=new Rect((getWidth() - actualAreaSize.width) / 2,(getHeight() - actualAreaSize.height) / 2,actualAreaSize.width,actualAreaSize.height);
Point newPoint=new Point(Math.max(actualArea.x,Math.min(actualArea.x + actualArea.width,startCenterPoint.x + translationX)),Math.max(actualArea.y,Math.min(actualArea.y + actualArea.height,startCenterPoint.y + translationY)));
centerPoint=new Point((newPoint.x - actualArea.x) / actualAreaSize.width,((newPoint.y - actualArea.y) + (actualAreaSize.width - actualAreaSize.height) / 2) / actualAreaSize.width);
}
break;
case BlurViewActiveControlInnerRadius:
{
float d=radialDistance - startDistance;
falloff=Math.min(Math.max(BlurMinimumFalloff,(startRadius + d) / shorterSide),size - BlurMinimumDifference);
}
break;
case BlurViewActiveControlOuterRadius:
{
float d=radialDistance - startDistance;
size=Math.max(falloff + BlurMinimumDifference,(startRadius + d) / shorterSide);
}
break;
default :
break;
}
}
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

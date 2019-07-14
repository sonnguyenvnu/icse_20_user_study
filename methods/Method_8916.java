private int getFrameRotation(){
switch (orientation) {
case 90:
{
      return Frame.ROTATION_90;
    }
case 180:
{
    return Frame.ROTATION_180;
  }
case 270:
{
  return Frame.ROTATION_270;
}
default :
{
return Frame.ROTATION_0;
}
}
}

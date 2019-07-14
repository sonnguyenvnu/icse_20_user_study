@Override public void run(){
  Camera.Parameters parameters=camera.getParameters();
  int width=parameters.getPreviewSize().width;
  int height=parameters.getPreviewSize().height;
  byte[] rotatedData=new Rotation(data,width,height,rotation).getYuv();
  int postWidth;
  int postHeight;
switch (rotation) {
case 90:
case 270:
    postWidth=height;
  postHeight=width;
break;
case 0:
case 180:
default :
postWidth=width;
postHeight=height;
break;
}
YuvImage yuv=new YuvImage(rotatedData,parameters.getPreviewFormat(),postWidth,postHeight,null);
onStillProcessedListener.onStillProcessed(yuv);
}

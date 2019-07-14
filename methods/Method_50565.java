private float[] getDrawable2Bounds(){
  for (int i=0; i < drawable2Bounds.length; i++) {
    drawable2Bounds[i]=0;
  }
  drawable2Width=(drawable2Width == 0 ? width / 2f : drawable2Width);
  drawable2Height=(drawable2Height == 0 ? height / 2f : drawable2Height);
switch (stateDrawable2Mode) {
case LEFT:
    drawable2Bounds[0]=0 + drawable2PaddingLeft;
  drawable2Bounds[1]=height / 2f - drawable2Height / 2f + drawable2PaddingTop;
drawable2Bounds[2]=drawable2Bounds[0] + drawable2Width;
drawable2Bounds[3]=drawable2Bounds[1] + drawable2Height;
break;
case TOP:
drawable2Bounds[0]=width / 2f - drawable2Width / 2f + drawable2PaddingLeft;
drawable2Bounds[1]=0 + drawable2PaddingTop;
drawable2Bounds[2]=drawable2Bounds[0] + drawable2Width;
drawable2Bounds[3]=drawable2Bounds[1] + drawable2Height;
break;
case RIGHT:
drawable2Bounds[0]=width - drawable2Width + drawable2PaddingLeft;
drawable2Bounds[1]=height / 2 - drawable2Height / 2 + drawable2PaddingTop;
drawable2Bounds[2]=drawable2Bounds[0] + drawable2Width;
drawable2Bounds[3]=drawable2Bounds[1] + drawable2Height;
break;
case BOTTOM:
drawable2Bounds[0]=width / 2f - drawable2Width / 2f + drawable2PaddingLeft;
drawable2Bounds[1]=height - drawable2Height + drawable2PaddingTop;
drawable2Bounds[2]=drawable2Bounds[0] + drawable2Width;
drawable2Bounds[3]=drawable2Bounds[1] + drawable2Height;
break;
case CENTER:
drawable2Bounds[0]=width / 2f - drawable2Width / 2f + drawable2PaddingLeft;
drawable2Bounds[1]=height / 2 - drawable2Height / 2 + drawable2PaddingTop;
drawable2Bounds[2]=drawable2Bounds[0] + drawable2Width;
drawable2Bounds[3]=drawable2Bounds[1] + drawable2Height;
break;
case FILL:
drawable2Bounds[0]=0;
drawable2Bounds[1]=0;
drawable2Bounds[2]=width;
drawable2Bounds[3]=height;
break;
case LEFT_TOP:
drawable2Bounds[0]=0 + drawable2PaddingLeft;
drawable2Bounds[1]=0 + drawable2PaddingTop;
drawable2Bounds[2]=drawable2Bounds[0] + drawable2Width;
drawable2Bounds[3]=drawable2Bounds[1] + drawable2Height;
break;
case RIGHT_TOP:
drawable2Bounds[0]=width - drawable2Width + drawable2PaddingLeft;
drawable2Bounds[1]=0 + drawable2PaddingTop;
drawable2Bounds[2]=drawable2Bounds[0] + drawable2Width;
drawable2Bounds[3]=drawable2Bounds[1] + drawable2Height;
break;
case LEFT_BOTTOM:
drawable2Bounds[0]=0 + drawable2PaddingLeft;
drawable2Bounds[1]=height - drawable2Height + drawable2PaddingTop;
drawable2Bounds[2]=drawable2Bounds[0] + drawable2Width;
drawable2Bounds[3]=drawable2Bounds[1] + drawable2Height;
break;
case RIGHT_BOTTOM:
drawable2Bounds[0]=width - drawable2Width + drawable2PaddingLeft;
drawable2Bounds[1]=height - drawable2Height + drawable2PaddingTop;
drawable2Bounds[2]=drawable2Bounds[0] + drawable2Width;
drawable2Bounds[3]=drawable2Bounds[1] + drawable2Height;
break;
}
return drawable2Bounds;
}

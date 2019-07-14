private Pair<Integer,Integer> getTargetedWidthHeight(){
  int targetWidth;
  int targetHeight;
switch (selectedSize) {
case SIZE_PREVIEW:
    int maxWidthForPortraitMode=getImageMaxWidth();
  int maxHeightForPortraitMode=getImageMaxHeight();
targetWidth=isLandScape ? maxHeightForPortraitMode : maxWidthForPortraitMode;
targetHeight=isLandScape ? maxWidthForPortraitMode : maxHeightForPortraitMode;
break;
case SIZE_640_480:
targetWidth=isLandScape ? 640 : 480;
targetHeight=isLandScape ? 480 : 640;
break;
case SIZE_1024_768:
targetWidth=isLandScape ? 1024 : 768;
targetHeight=isLandScape ? 768 : 1024;
break;
default :
throw new IllegalStateException("Unknown size");
}
return new Pair<>(targetWidth,targetHeight);
}

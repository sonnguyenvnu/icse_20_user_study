public final View createMainView(Context context,int imageType,File imageFile,int initScaleType){
switch (imageType) {
case ImageInfoExtractor.TYPE_GIF:
case ImageInfoExtractor.TYPE_ANIMATED_WEBP:
    return createAnimatedImageView(context,imageType,imageFile,initScaleType);
case ImageInfoExtractor.TYPE_STILL_WEBP:
case ImageInfoExtractor.TYPE_STILL_IMAGE:
default :
  return createStillImageView(context);
}
}

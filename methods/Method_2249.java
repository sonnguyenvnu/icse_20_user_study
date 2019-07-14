@Override public ForwardingDrawable buildActualImageWrapper(ImageOptions imageOptions){
  ScaleTypeDrawable wrapper=new ScaleTypeDrawable(NOP_DRAWABLE,imageOptions.getActualImageScaleType(),imageOptions.getActualImageFocusPoint());
  ColorFilter actualImageColorFilter=imageOptions.getActualImageColorFilter();
  if (actualImageColorFilter != null) {
    wrapper.setColorFilter(actualImageColorFilter);
  }
  return wrapper;
}

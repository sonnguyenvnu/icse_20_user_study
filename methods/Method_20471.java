/** 
 * Set the image source from a bitmap, resource, asset, file or other URI, providing a preview image to be displayed until the full size image is loaded, starting with a given orientation setting, scale and center. This is the best method to use when you want scale and center to be restored after screen orientation change; it avoids any redundant loading of tiles in the wrong orientation. You must declare the dimensions of the full size image by calling  {@link ImageSource#dimensions(int,int)}on the imageSource object. The preview source will be ignored if you don't provide dimensions, and if you provide a bitmap for the full size image.
 * @param imageSource Image source. Dimensions must be declared.
 * @param previewSource Optional source for a preview image to be displayed and allow interaction while the full size image loads.
 * @param state State to be restored. Nullable.
 */
public final void setImage(@NonNull ImageSource imageSource,ImageSource previewSource,ImageViewState state){
  if (imageSource == null) {
    throw new NullPointerException("imageSource must not be null");
  }
  reset(true);
  if (state != null) {
    restoreState(state);
  }
  if (previewSource != null) {
    if (imageSource.getBitmap() != null) {
      throw new IllegalArgumentException("Preview image cannot be used when a bitmap is provided for the main image");
    }
    if (imageSource.getSWidth() <= 0 || imageSource.getSHeight() <= 0) {
      throw new IllegalArgumentException("Preview image cannot be used unless dimensions are provided for the main image");
    }
    this.sWidth=imageSource.getSWidth();
    this.sHeight=imageSource.getSHeight();
    this.pRegion=previewSource.getSRegion();
    if (previewSource.getBitmap() != null) {
      this.bitmapIsCached=previewSource.isCached();
      onPreviewLoaded(previewSource.getBitmap());
    }
 else {
      Uri uri=previewSource.getUri();
      if (uri == null && previewSource.getResource() != null) {
        uri=Uri.parse(ContentResolver.SCHEME_ANDROID_RESOURCE + "://" + getContext().getPackageName() + "/" + previewSource.getResource());
      }
      BitmapLoadTask task=new BitmapLoadTask(this,getContext(),bitmapDecoderFactory,uri,true);
      execute(task);
    }
  }
  if (imageSource.getBitmap() != null && imageSource.getSRegion() != null) {
    onImageLoaded(Bitmap.createBitmap(imageSource.getBitmap(),imageSource.getSRegion().left,imageSource.getSRegion().top,imageSource.getSRegion().width(),imageSource.getSRegion().height()),ORIENTATION_0,false);
  }
 else   if (imageSource.getBitmap() != null) {
    onImageLoaded(imageSource.getBitmap(),ORIENTATION_0,imageSource.isCached());
  }
 else {
    sRegion=imageSource.getSRegion();
    uri=imageSource.getUri();
    if (uri == null && imageSource.getResource() != null) {
      uri=Uri.parse(ContentResolver.SCHEME_ANDROID_RESOURCE + "://" + getContext().getPackageName() + "/" + imageSource.getResource());
    }
    if (imageSource.getTile() || sRegion != null) {
      TilesInitTask task=new TilesInitTask(this,getContext(),regionDecoderFactory,uri);
      execute(task);
    }
 else {
      BitmapLoadTask task=new BitmapLoadTask(this,getContext(),bitmapDecoderFactory,uri,false);
      execute(task);
    }
  }
}

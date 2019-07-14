/** 
 * Sets the content of this GifImageView to the specified Uri. If uri destination is not a GIF then  {@link android.widget.ImageView#setImageURI(android.net.Uri)}is called as fallback. For supported URI schemes see:  {@link android.content.ContentResolver#openAssetFileDescriptor(android.net.Uri,String)}.
 * @param uri The Uri of an image
 */
@Override public void setImageURI(Uri uri){
  if (!GifViewUtils.setGifImageUri(this,uri)) {
    super.setImageURI(uri);
  }
}

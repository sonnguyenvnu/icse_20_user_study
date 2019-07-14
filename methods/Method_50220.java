/** 
 * ???? ImageLoaderType.GLIDE
 */
public static RxGalleryFinalApi getInstance(Activity context){
  if (context == null) {
    throw new NullPointerException("context == null");
  }
  rxGalleryFinal=RxGalleryFinal.with(context).imageLoader(ImageLoaderType.GLIDE).subscribe(null);
  Logger.i("==========" + mRxApi + "====" + rxGalleryFinal);
  return mRxApi;
}

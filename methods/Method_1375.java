public static @Nullable WebpBitmapFactory loadWebpBitmapFactoryIfExists(){
  if (sWebpLibraryChecked) {
    return sWebpBitmapFactory;
  }
  WebpBitmapFactory loadedWebpBitmapFactory=null;
  try {
    loadedWebpBitmapFactory=(WebpBitmapFactory)Class.forName("com.facebook.webpsupport.WebpBitmapFactoryImpl").newInstance();
  }
 catch (  Throwable e) {
  }
  sWebpLibraryChecked=true;
  return loadedWebpBitmapFactory;
}

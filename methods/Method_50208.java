public static RxGalleryFinal with(@NonNull Context context){
  RxGalleryFinal instance=new RxGalleryFinal();
  instance.configuration.setContext(context.getApplicationContext());
  return instance;
}

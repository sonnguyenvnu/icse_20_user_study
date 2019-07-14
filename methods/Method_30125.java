@Override public void registerComponents(Context context,Glide glide,Registry registry){
  registry.prepend(File.class,ImageInfo.class,new FileImageInfoDecoder());
}

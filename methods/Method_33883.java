@Bean public PhotoServiceImpl photoServices(){
  List<PhotoInfo> photos=new ArrayList<PhotoInfo>();
  photos.add(createPhoto("1","marissa"));
  photos.add(createPhoto("2","paul"));
  photos.add(createPhoto("3","marissa"));
  photos.add(createPhoto("4","paul"));
  photos.add(createPhoto("5","marissa"));
  photos.add(createPhoto("6","paul"));
  PhotoServiceImpl photoServices=new PhotoServiceImpl();
  photoServices.setPhotos(photos);
  return photoServices;
}

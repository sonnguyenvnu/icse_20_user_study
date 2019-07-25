@Bean public ImageFilePath img(){
  LocalImageFilePathUtils localImageFilePathUtils=new LocalImageFilePathUtils();
  localImageFilePathUtils.setBasePath("/static");
  return localImageFilePathUtils;
}

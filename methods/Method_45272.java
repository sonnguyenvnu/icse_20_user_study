public static ContentResource fileResource(final Resource filename,final Charset charset,final MocoConfig config){
  return contentResource(id(MocoConfig.FILE_ID),fileConfigApplier(MocoConfig.FILE_ID,filename),new FileResourceReader(filename,charset,config));
}

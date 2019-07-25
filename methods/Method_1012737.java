@Override public void parse(DLNAMediaInfo media,InputFile file,int type,RendererConfiguration renderer){
  boolean trace=LOGGER.isTraceEnabled();
  if (media == null || file == null || file.getFile() == null) {
    if (trace) {
      if (file != null && file.getFile() != null) {
        LOGGER.trace("Not parsing RAW file \"{}\" because media is null",file.getFile().getName());
      }
 else {
        LOGGER.error("Not parsing RAW file because file is null");
      }
    }
    return;
  }
  PmsConfiguration configuration=PMS.getConfiguration(renderer);
  try {
    DCRaw dcraw=(DCRaw)PlayerFactory.getActivePlayer(DCRaw.ID);
    if (dcraw != null) {
      if (trace) {
        LOGGER.trace("Parsing RAW image \"{}\" with DCRaw",file.getFile().getName());
      }
      dcraw.parse(media,file.getFile());
      media.setCodecV(FormatConfiguration.RAW);
      media.setContainer(FormatConfiguration.RAW);
      ImageInfo imageInfo=null;
      Metadata metadata=null;
      FileType fileType=null;
      try (BufferedInputStream inputStream=new BufferedInputStream(Files.newInputStream(file.getFile().toPath()))){
        fileType=FileTypeDetector.detectFileType(inputStream);
        metadata=ImagesUtil.getMetadata(inputStream,fileType);
      }
 catch (      IOException e) {
        metadata=new Metadata();
        LOGGER.debug("Error reading \"{}\": {}",file.getFile().getAbsolutePath(),e.getMessage());
        LOGGER.trace("",e);
      }
catch (      ImageProcessingException e) {
        metadata=new Metadata();
        LOGGER.debug("Error parsing {} metadata for \"{}\": {}",fileType.toString().toUpperCase(Locale.ROOT),file.getFile().getAbsolutePath(),e.getMessage());
        LOGGER.trace("",e);
      }
      if (fileType == FileType.Arw && !ImagesUtil.isARW(metadata)) {
        fileType=FileType.Tiff;
      }
      ImageFormat format=ImageFormat.toImageFormat(fileType);
      if (format == null || format == ImageFormat.TIFF) {
        format=ImageFormat.toImageFormat(metadata);
        if (format == null || format == ImageFormat.TIFF) {
          format=ImageFormat.RAW;
        }
      }
      try {
        imageInfo=ImageInfo.create(media.getWidth(),media.getHeight(),metadata,format,file.getSize(),true,false);
        if (trace) {
          LOGGER.trace("Parsing of RAW image \"{}\" completed: {}",file.getFile().getName(),imageInfo);
        }
      }
 catch (      ParseException e) {
        LOGGER.warn("Unable to parse \"{}\": {}",file.getFile().getAbsolutePath(),e.getMessage());
        LOGGER.trace("",e);
      }
      media.setImageInfo(imageInfo);
      if (media.getWidth() > 0 && media.getHeight() > 0 && configuration.getImageThumbnailsEnabled()) {
        byte[] image=dcraw.getThumbnail(null,file.getFile().getAbsolutePath(),imageInfo);
        media.setThumb(DLNAThumbnail.toThumbnail(image,320,320,ScaleType.MAX,ImageFormat.JPEG,false));
      }
    }
 else {
      if (trace) {
        LOGGER.trace("Parsing RAW image \"{}\" as a regular image because DCRaw is disabled",file.getFile().getName());
      }
      ImagesUtil.parseImage(file.getFile(),media);
    }
    media.setSize(file.getSize());
    media.setImageCount(1);
    media.postParse(type,file);
    media.setMediaparsed(true);
  }
 catch (  IOException e) {
    LOGGER.error("Error parsing RAW file \"{}\": {}",file.getFile().getAbsolutePath(),e.getMessage());
    LOGGER.trace("",e);
  }
}

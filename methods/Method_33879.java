@RequestMapping("/sparklr/photos/{id}") public ResponseEntity<BufferedImage> photo(@PathVariable String id) throws Exception {
  InputStream photo=sparklrService.loadSparklrPhoto(id);
  if (photo == null) {
    throw new UnavailableException("The requested photo does not exist");
  }
  BufferedImage body;
  MediaType contentType=MediaType.IMAGE_JPEG;
  Iterator<ImageReader> imageReaders=ImageIO.getImageReadersByMIMEType(contentType.toString());
  if (imageReaders.hasNext()) {
    ImageReader imageReader=imageReaders.next();
    ImageReadParam irp=imageReader.getDefaultReadParam();
    imageReader.setInput(new MemoryCacheImageInputStream(photo),true);
    body=imageReader.read(0,irp);
  }
 else {
    throw new HttpMessageNotReadableException("Could not find javax.imageio.ImageReader for Content-Type [" + contentType + "]");
  }
  HttpHeaders headers=new HttpHeaders();
  headers.setContentType(MediaType.IMAGE_JPEG);
  return new ResponseEntity<BufferedImage>(body,headers,HttpStatus.OK);
}

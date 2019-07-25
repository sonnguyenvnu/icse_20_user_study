/** 
 * Performs the Jpeg data extraction, adding found values to the specified instance of <code>Metadata</code>.
 */
public void extract(@NotNull final BufferReader reader,@NotNull Metadata metadata){
  JpegDirectory directory=metadata.getOrCreateDirectory(JpegDirectory.class);
  try {
    int dataPrecision=reader.getUInt8(JpegDirectory.TAG_JPEG_DATA_PRECISION);
    directory.setInt(JpegDirectory.TAG_JPEG_DATA_PRECISION,dataPrecision);
    int height=reader.getUInt16(JpegDirectory.TAG_JPEG_IMAGE_HEIGHT);
    directory.setInt(JpegDirectory.TAG_JPEG_IMAGE_HEIGHT,height);
    int width=reader.getUInt16(JpegDirectory.TAG_JPEG_IMAGE_WIDTH);
    directory.setInt(JpegDirectory.TAG_JPEG_IMAGE_WIDTH,width);
    int numberOfComponents=reader.getUInt8(JpegDirectory.TAG_JPEG_NUMBER_OF_COMPONENTS);
    directory.setInt(JpegDirectory.TAG_JPEG_NUMBER_OF_COMPONENTS,numberOfComponents);
    int offset=6;
    for (int i=0; i < numberOfComponents; i++) {
      int componentId=reader.getUInt8(offset++);
      int samplingFactorByte=reader.getUInt8(offset++);
      int quantizationTableNumber=reader.getUInt8(offset++);
      JpegComponent component=new JpegComponent(componentId,samplingFactorByte,quantizationTableNumber);
      directory.setObject(JpegDirectory.TAG_JPEG_COMPONENT_DATA_1 + i,component);
    }
  }
 catch (  BufferBoundsException ex) {
    directory.addError(ex.getMessage());
  }
}

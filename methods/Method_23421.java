private IIOMetadata imageioDPI(ImageWriter writer,ImageWriteParam param,double dpi){
  ImageTypeSpecifier typeSpecifier=ImageTypeSpecifier.createFromBufferedImageType(BufferedImage.TYPE_INT_RGB);
  IIOMetadata metadata=writer.getDefaultImageMetadata(typeSpecifier,param);
  if (!metadata.isReadOnly() && metadata.isStandardMetadataFormatSupported()) {
    double dotsPerMilli=dpi / 25.4;
    IIOMetadataNode horiz=new IIOMetadataNode("HorizontalPixelSize");
    horiz.setAttribute("value",Double.toString(dotsPerMilli));
    IIOMetadataNode vert=new IIOMetadataNode("VerticalPixelSize");
    vert.setAttribute("value",Double.toString(dotsPerMilli));
    IIOMetadataNode dim=new IIOMetadataNode("Dimension");
    dim.appendChild(horiz);
    dim.appendChild(vert);
    IIOMetadataNode root=new IIOMetadataNode("javax_imageio_1.0");
    root.appendChild(dim);
    try {
      metadata.mergeTree("javax_imageio_1.0",root);
      return metadata;
    }
 catch (    IIOInvalidTreeException e) {
      System.err.println("Could not set the DPI of the output image");
      e.printStackTrace();
    }
  }
  return null;
}

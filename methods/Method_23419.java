/** 
 * Use ImageIO functions from Java 1.4 and later to handle image save. Various formats are supported, typically jpeg, png, bmp, and wbmp. To get a list of the supported formats for writing, use: <BR> <TT>println(javax.imageio.ImageIO.getReaderFormatNames())</TT>
 */
protected boolean saveImageIO(String path) throws IOException {
  try {
    int outputFormat=(format == ARGB) ? BufferedImage.TYPE_INT_ARGB : BufferedImage.TYPE_INT_RGB;
    String extension=path.substring(path.lastIndexOf('.') + 1).toLowerCase();
    if (extension.equals("bmp") || extension.equals("jpg") || extension.equals("jpeg")) {
      outputFormat=BufferedImage.TYPE_INT_RGB;
    }
    BufferedImage bimage=new BufferedImage(pixelWidth,pixelHeight,outputFormat);
    bimage.setRGB(0,0,pixelWidth,pixelHeight,pixels,0,pixelWidth);
    File file=new File(path);
    ImageWriter writer=null;
    ImageWriteParam param=null;
    IIOMetadata metadata=null;
    if (extension.equals("jpg") || extension.equals("jpeg")) {
      if ((writer=imageioWriter("jpeg")) != null) {
        param=writer.getDefaultWriteParam();
        param.setCompressionMode(ImageWriteParam.MODE_EXPLICIT);
        param.setCompressionQuality(0.9f);
      }
    }
    if (extension.equals("png")) {
      if ((writer=imageioWriter("png")) != null) {
        param=writer.getDefaultWriteParam();
        if (false) {
          metadata=imageioDPI(writer,param,100);
        }
      }
    }
    if (writer != null) {
      BufferedOutputStream output=new BufferedOutputStream(PApplet.createOutput(file));
      writer.setOutput(ImageIO.createImageOutputStream(output));
      writer.write(metadata,new IIOImage(bimage,null,metadata),param);
      writer.dispose();
      output.flush();
      output.close();
      return true;
    }
    return javax.imageio.ImageIO.write(bimage,extension,file);
  }
 catch (  Exception e) {
    e.printStackTrace();
    throw new IOException("image save failed.");
  }
}

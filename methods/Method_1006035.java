@Override public Image render(int width,int height){
  PDFRenderer renderer=new PDFRenderer(document);
  try {
    int resolution=96;
    BufferedImage image=renderer.renderImageWithDPI(pageNumber,2 * resolution,ImageType.RGB);
    return SwingFXUtils.toFXImage(resize(image,width,height),null);
  }
 catch (  IOException e) {
    return null;
  }
}

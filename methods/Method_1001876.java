private void copy(){
  final GeneratedImage generatedImage=simpleLine2.getGeneratedImage();
  if (generatedImage == null) {
    return;
  }
  final File png=generatedImage.getPngFile();
  final Image image=Toolkit.getDefaultToolkit().createImage(png.getAbsolutePath());
  final ImageSelection imgSel=new ImageSelection(image);
  Toolkit.getDefaultToolkit().getSystemClipboard().setContents(imgSel,null);
}

public Image makeGradient(String attribute,int wide,int high){
  int top=getColor(attribute + ".gradient.top").getRGB();
  int bot=getColor(attribute + ".gradient.bottom").getRGB();
  BufferedImage outgoing=new BufferedImage(wide,high,BufferedImage.TYPE_INT_RGB);
  int[] row=new int[wide];
  WritableRaster wr=outgoing.getRaster();
  for (int i=0; i < high; i++) {
    int rgb=PApplet.lerpColor(top,bot,i / (float)(high - 1),PConstants.RGB);
    Arrays.fill(row,rgb);
    wr.setDataElements(0,i,wide,1,row);
  }
  return outgoing;
}

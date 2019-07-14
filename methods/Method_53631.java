public static void save(ActionEvent e,CycleView graph){
  int w=graph.getWidth();
  int h=graph.getHeight();
  BufferedImage img=new BufferedImage(w,h,BufferedImage.TYPE_INT_RGB);
  graph.paint(img.createGraphics());
  JFileChooser chooser=new JFileChooser(new File(System.getProperty("user.home")));
  int c=chooser.showSaveDialog(graph);
  if (c == JFileChooser.CANCEL_OPTION) {
    System.out.println("cancel");
    return;
  }
  try {
    File f=chooser.getSelectedFile();
    ImageIO.write(img,"png",f);
    System.out.println(f.getAbsolutePath());
    Desktop.getDesktop().open(f.getParentFile());
  }
 catch (  IOException e1) {
    e1.printStackTrace();
  }
}

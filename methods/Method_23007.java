/** 
 * Get an icon of the format base-NN.png where NN is the size, but if it's a hidpi display, get the NN*2 version automatically, sized at NN
 */
static public ImageIcon getIconX(File dir,String base,int size){
  final int scale=Toolkit.highResImages() ? 2 : 1;
  String filename=(size == 0) ? (base + "-" + scale + "x.png") : (base + "-" + (size * scale) + ".png");
  File file=new File(dir,filename);
  if (!file.exists()) {
    return null;
  }
  ImageIcon outgoing=new ImageIcon(file.getAbsolutePath()){
    @Override public int getIconWidth(){
      return Toolkit.zoom(super.getIconWidth()) / scale;
    }
    @Override public int getIconHeight(){
      return Toolkit.zoom(super.getIconHeight()) / scale;
    }
    @Override public synchronized void paintIcon(    Component c,    Graphics g,    int x,    int y){
      ImageObserver imageObserver=getImageObserver();
      if (imageObserver == null) {
        imageObserver=c;
      }
      g.drawImage(getImage(),x,y,getIconWidth(),getIconHeight(),imageObserver);
    }
  }
;
  return outgoing;
}

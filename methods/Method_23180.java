protected void setProcessingIcon(Frame frame){
  if (PApplet.platform != PConstants.MACOSX) {
    try {
      if (iconImages == null) {
        iconImages=new ArrayList<Image>();
        final int[] sizes={16,32,48,64,128,256,512};
        for (        int sz : sizes) {
          URL url=PApplet.class.getResource("/icon/icon-" + sz + ".png");
          Image image=Toolkit.getDefaultToolkit().getImage(url);
          iconImages.add(image);
        }
      }
      frame.setIconImages(iconImages);
    }
 catch (    Exception e) {
    }
  }
 else {
    if (!dockIconSpecified()) {
      URL url=PApplet.class.getResource("/icon/icon-512.png");
      try {
        final String td="processing.core.ThinkDifferent";
        Class<?> thinkDifferent=Thread.currentThread().getContextClassLoader().loadClass(td);
        Method method=thinkDifferent.getMethod("setIconImage",new Class[]{java.awt.Image.class});
        method.invoke(null,new Object[]{Toolkit.getDefaultToolkit().getImage(url)});
      }
 catch (      Exception e) {
        e.printStackTrace();
      }
    }
  }
}

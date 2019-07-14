protected void initIcons(){
  IOUtil.ClassResources res=null;
  if (PJOGL.icons == null || PJOGL.icons.length == 0) {
    final int[] sizes={16,32,48,64,128,256,512};
    String[] iconImages=new String[sizes.length];
    for (int i=0; i < sizes.length; i++) {
      iconImages[i]="/icon/icon-" + sizes[i] + ".png";
    }
    res=new ClassResources(iconImages,PApplet.class.getClassLoader(),PApplet.class);
  }
 else {
    String[] iconImages=new String[PJOGL.icons.length];
    for (int i=0; i < PJOGL.icons.length; i++) {
      iconImages[i]=resourceFilename(PJOGL.icons[i]);
    }
    res=new ClassResources(iconImages,sketch.getClass().getClassLoader(),sketch.getClass());
  }
  NewtFactory.setWindowIcons(res);
}

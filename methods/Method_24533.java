static protected DefaultFontMapper getMapper(){
  if (mapper == null) {
    mapper=new DefaultFontMapper();
    if (PApplet.platform == PConstants.MACOSX) {
      try {
        String homeLibraryFonts=System.getProperty("user.home") + "/Library/Fonts";
        mapper.insertDirectory(homeLibraryFonts);
      }
 catch (      Exception e) {
      }
      mapper.insertDirectory("/System/Library/Fonts");
      mapper.insertDirectory("/Library/Fonts");
    }
 else     if (PApplet.platform == PConstants.WINDOWS) {
      File roots[]=File.listRoots();
      for (int i=0; i < roots.length; i++) {
        if (roots[i].toString().startsWith("A:")) {
          continue;
        }
        File folder=new File(roots[i],"WINDOWS/Fonts");
        if (folder.exists()) {
          mapper.insertDirectory(folder.getAbsolutePath());
          break;
        }
        folder=new File(roots[i],"WINNT/Fonts");
        if (folder.exists()) {
          mapper.insertDirectory(folder.getAbsolutePath());
          break;
        }
      }
    }
 else     if (PApplet.platform == PConstants.LINUX) {
      checkDir("/usr/share/fonts/",mapper);
      checkDir("/usr/local/share/fonts/",mapper);
      checkDir(System.getProperty("user.home") + "/.fonts",mapper);
    }
  }
  return mapper;
}

/** 
 * Read an image from a file. ImageIcon doesn't don't do well with some file types, so we use ImageIO. ImageIO doesn't handle TGA files created by Processing, so this calls our own loadImageTGA(). <br> Prints errors itself.
 * @return null on error; image only if okay.
 */
private BufferedImage readImage(File file){
  try {
    Thread current=Thread.currentThread();
    ClassLoader origLoader=Thread.currentThread().getContextClassLoader();
    current.setContextClassLoader(getClass().getClassLoader());
    BufferedImage image;
    try {
      image=ImageIO.read(file);
    }
 catch (    IOException e) {
      System.err.println(Language.interpolate("movie_maker.error.cannot_read",file.getAbsolutePath()));
      return null;
    }
    current.setContextClassLoader(origLoader);
    if (image == null) {
      String path=file.getAbsolutePath();
      String pathLower=path.toLowerCase();
      if (pathLower.endsWith(".tga")) {
        try {
          return loadImageTGA(file);
        }
 catch (        IOException e) {
          cannotRead(file);
          return null;
        }
      }
 else       if (pathLower.endsWith(".tif") || pathLower.endsWith(".tiff")) {
        cannotRead(file);
        System.err.println(Language.text("movie_maker.error.avoid_tiff"));
        return null;
      }
 else {
        cannotRead(file);
        return null;
      }
    }
 else {
      if (image.getWidth() <= 0 || image.getHeight() <= 0) {
        System.err.println(Language.interpolate("movie_maker.error.cannot_read_maybe_bad",file.getAbsolutePath()));
        return null;
      }
    }
    return image;
  }
 catch (  RuntimeException e) {
    cannotRead(file);
    return null;
  }
}

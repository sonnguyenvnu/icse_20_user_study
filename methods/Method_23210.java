/** 
 * Version of createGraphics() used internally.
 * @param path A path (or null if none), can be absolute or relative ({@link PApplet#savePath} will be called)
 */
protected PGraphics makeGraphics(int w,int h,String renderer,String path,boolean primary){
  if (!primary && !g.isGL()) {
    if (renderer.equals(P2D)) {
      throw new RuntimeException("createGraphics() with P2D requires size() to use P2D or P3D");
    }
 else     if (renderer.equals(P3D)) {
      throw new RuntimeException("createGraphics() with P3D or OPENGL requires size() to use P2D or P3D");
    }
  }
  try {
    Class<?> rendererClass=Thread.currentThread().getContextClassLoader().loadClass(renderer);
    Constructor<?> constructor=rendererClass.getConstructor(new Class[]{});
    PGraphics pg=(PGraphics)constructor.newInstance();
    pg.setParent(this);
    pg.setPrimary(primary);
    if (path != null) {
      pg.setPath(savePath(path));
    }
    pg.setSize(w,h);
    return pg;
  }
 catch (  InvocationTargetException ite) {
    String msg=ite.getTargetException().getMessage();
    if ((msg != null) && (msg.indexOf("no jogl in java.library.path") != -1)) {
      throw new RuntimeException("The jogl library folder needs to be " + "specified with -Djava.library.path=/path/to/jogl");
    }
 else {
      printStackTrace(ite.getTargetException());
      Throwable target=ite.getTargetException();
      throw new RuntimeException(target.getMessage());
    }
  }
catch (  ClassNotFoundException cnfe) {
    if (external) {
      throw new RuntimeException("You need to use \"Import Library\" " + "to add " + renderer + " to your sketch.");
    }
 else {
      throw new RuntimeException("The " + renderer + " renderer is not in the class path.");
    }
  }
catch (  Exception e) {
    if ((e instanceof IllegalArgumentException) || (e instanceof NoSuchMethodException) || (e instanceof IllegalAccessException)) {
      if (e.getMessage().contains("cannot be <= 0")) {
        throw new RuntimeException(e);
      }
 else {
        printStackTrace(e);
        String msg=renderer + " needs to be updated " + "for the current release of Processing.";
        throw new RuntimeException(msg);
      }
    }
 else {
      printStackTrace(e);
      throw new RuntimeException(e.getMessage());
    }
  }
}

public static PicassoUtils getinstance(){
  if (instance == null) {
synchronized (PicassoUtils.class) {
      if (instance == null) {
        instance=new PicassoUtils();
      }
    }
  }
  return instance;
}

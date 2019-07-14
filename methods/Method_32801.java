public static int getPid(Process p){
  try {
    Field f=p.getClass().getDeclaredField("pid");
    f.setAccessible(true);
    try {
      return f.getInt(p);
    }
  finally {
      f.setAccessible(false);
    }
  }
 catch (  Throwable e) {
    return -1;
  }
}

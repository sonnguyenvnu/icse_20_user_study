private void persistToFile(){
  new Thread(new Runnable(){
    @Override public void run(){
      User user=new User(1,"hello world",false);
      File dir=new File(MyConstants.CHAPTER_2_PATH);
      if (!dir.exists()) {
        dir.mkdirs();
      }
      File cachedFile=new File(MyConstants.CACHE_FILE_PATH);
      ObjectOutputStream objectOutputStream=null;
      try {
        objectOutputStream=new ObjectOutputStream(new FileOutputStream(cachedFile));
        objectOutputStream.writeObject(user);
        Log.d(TAG,"persist user:" + user);
      }
 catch (      IOException e) {
        e.printStackTrace();
      }
 finally {
        MyUtils.close(objectOutputStream);
      }
    }
  }
).start();
}

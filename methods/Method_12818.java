private void recoverFromFile(){
  new Thread(new Runnable(){
    @Override public void run(){
      User user=null;
      File cachedFile=new File(MyConstants.CACHE_FILE_PATH);
      if (cachedFile.exists()) {
        ObjectInputStream objectInputStream=null;
        try {
          objectInputStream=new ObjectInputStream(new FileInputStream(cachedFile));
          user=(User)objectInputStream.readObject();
          Log.d(TAG,"recover user:" + user);
        }
 catch (        IOException e) {
          e.printStackTrace();
        }
catch (        ClassNotFoundException e) {
          e.printStackTrace();
        }
 finally {
          MyUtils.close(objectInputStream);
        }
      }
    }
  }
).start();
}

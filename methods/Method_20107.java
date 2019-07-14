private Task<String> loadStringFromFile(){
  return Tasks.call(AsyncTask.THREAD_POOL_EXECUTOR,new Callable<String>(){
    @Override public String call() throws Exception {
      File contentFile=new File(getFilesDir(),CONTENT_FILE);
      if (contentFile.createNewFile()) {
        InputStream is;
        is=getAssets().open(DEFAULT_CONTENT_FILE);
        int size=is.available();
        byte[] buffer=new byte[size];
        is.read(buffer);
        is.close();
        FileOutputStream fos=new FileOutputStream(contentFile);
        fos.write(buffer);
        fos.close();
      }
      FileInputStream fis=new FileInputStream(contentFile);
      byte[] content=new byte[(int)contentFile.length()];
      fis.read(content);
      return new String(content);
    }
  }
);
}

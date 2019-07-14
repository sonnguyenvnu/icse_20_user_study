public boolean load(API api,URI uri){
  File file=new File(uri.getPath());
  FileLoader fileLoader=api.getFileLoader(file);
  return (fileLoader != null) && fileLoader.load(api,file);
}

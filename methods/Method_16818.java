public static State saveBinaryFile(byte[] data,String path){
  File file=new File(path);
  State state=valid(file);
  if (!state.isSuccess()) {
    return state;
  }
  try (BufferedOutputStream bos=new BufferedOutputStream(new FileOutputStream(file))){
    bos.write(data);
    bos.flush();
    bos.close();
  }
 catch (  IOException ioe) {
    return new BaseState(false,AppInfo.IO_ERROR);
  }
  state=new BaseState(true,file.getAbsolutePath());
  state.putInfo("size",data.length);
  state.putInfo("title",file.getName());
  return state;
}

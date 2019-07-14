private static FileDescriptor wrapFileDescriptor(int fileDescriptor){
  FileDescriptor result=new FileDescriptor();
  try {
    Field descriptorField;
    try {
      descriptorField=FileDescriptor.class.getDeclaredField("descriptor");
    }
 catch (    NoSuchFieldException e) {
      descriptorField=FileDescriptor.class.getDeclaredField("fd");
    }
    descriptorField.setAccessible(true);
    descriptorField.set(result,fileDescriptor);
  }
 catch (  NoSuchFieldException|IllegalAccessException|IllegalArgumentException e) {
    Log.wtf(EmulatorDebug.LOG_TAG,"Error accessing FileDescriptor#descriptor private field",e);
    System.exit(1);
  }
  return result;
}

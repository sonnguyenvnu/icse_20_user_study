private static synchronized void insertNativeLibrary(DexClassLoader dexClassLoader,ClassLoader baseClassLoader,File nativeLibsDir) throws Exception {
  if (sHasInsertedNativeLibrary) {
    return;
  }
  sHasInsertedNativeLibrary=true;
  Context context=ActivityThread.currentApplication();
  Object basePathList=getPathList(baseClassLoader);
  if (Build.VERSION.SDK_INT > Build.VERSION_CODES.LOLLIPOP_MR1) {
    Reflector reflector=Reflector.with(basePathList);
    List<File> nativeLibraryDirectories=reflector.field("nativeLibraryDirectories").get();
    nativeLibraryDirectories.add(nativeLibsDir);
    Object baseNativeLibraryPathElements=reflector.field("nativeLibraryPathElements").get();
    final int baseArrayLength=Array.getLength(baseNativeLibraryPathElements);
    Object newPathList=getPathList(dexClassLoader);
    Object newNativeLibraryPathElements=reflector.get(newPathList);
    Class<?> elementClass=newNativeLibraryPathElements.getClass().getComponentType();
    Object allNativeLibraryPathElements=Array.newInstance(elementClass,baseArrayLength + 1);
    System.arraycopy(baseNativeLibraryPathElements,0,allNativeLibraryPathElements,0,baseArrayLength);
    Field soPathField;
    if (Build.VERSION.SDK_INT >= 26) {
      soPathField=elementClass.getDeclaredField("path");
    }
 else {
      soPathField=elementClass.getDeclaredField("dir");
    }
    soPathField.setAccessible(true);
    final int newArrayLength=Array.getLength(newNativeLibraryPathElements);
    for (int i=0; i < newArrayLength; i++) {
      Object element=Array.get(newNativeLibraryPathElements,i);
      String dir=((File)soPathField.get(element)).getAbsolutePath();
      if (dir.contains(Constants.NATIVE_DIR)) {
        Array.set(allNativeLibraryPathElements,baseArrayLength,element);
        break;
      }
    }
    reflector.set(allNativeLibraryPathElements);
  }
 else {
    Reflector reflector=Reflector.with(basePathList).field("nativeLibraryDirectories");
    File[] nativeLibraryDirectories=reflector.get();
    final int N=nativeLibraryDirectories.length;
    File[] newNativeLibraryDirectories=new File[N + 1];
    System.arraycopy(nativeLibraryDirectories,0,newNativeLibraryDirectories,0,N);
    newNativeLibraryDirectories[N]=nativeLibsDir;
    reflector.set(newNativeLibraryDirectories);
  }
}

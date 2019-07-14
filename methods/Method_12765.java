public static void insertDex(DexClassLoader dexClassLoader,ClassLoader baseClassLoader,File nativeLibsDir) throws Exception {
  Object baseDexElements=getDexElements(getPathList(baseClassLoader));
  Object newDexElements=getDexElements(getPathList(dexClassLoader));
  Object allDexElements=combineArray(baseDexElements,newDexElements);
  Object pathList=getPathList(baseClassLoader);
  Reflector.with(pathList).field("dexElements").set(allDexElements);
  insertNativeLibrary(dexClassLoader,baseClassLoader,nativeLibsDir);
}

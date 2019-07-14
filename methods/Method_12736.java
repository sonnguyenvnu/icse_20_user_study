protected ClassLoader createClassLoader(Context context,File apk,File libsDir,ClassLoader parent) throws Exception {
  File dexOutputDir=getDir(context,Constants.OPTIMIZE_DIR);
  String dexOutputPath=dexOutputDir.getAbsolutePath();
  DexClassLoader loader=new DexClassLoader(apk.getAbsolutePath(),dexOutputPath,libsDir.getAbsolutePath(),parent);
  if (Constants.COMBINE_CLASSLOADER) {
    DexUtil.insertDex(loader,parent,libsDir);
  }
  return loader;
}

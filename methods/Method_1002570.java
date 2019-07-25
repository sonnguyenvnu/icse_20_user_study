/** 
 * Construct map from fully qualified class name to filename whose sources are found under a given source directory. All source files are required to have an extension.
 * @param sourceDir the source directory to scan
 * @param requiredExtension only include files whose extension equals to this parameternull if no specific extension is required
 * @return map from fully qualified class name to filename for scanned source files.
 */
public static Map<String,String> scan(String sourceDir,String requiredExtension){
  final String sourceDirWithSeparator=sourceDir.endsWith(File.separator) ? sourceDir : sourceDir + File.separator;
  final File dir=new File(sourceDirWithSeparator);
  if (!dir.exists() || !dir.isDirectory()) {
    return Collections.emptyMap();
  }
  final Collection<File> files=FileUtils.listFiles(dir,null,true);
  final Map<String,String> classFileNames=new HashMap<String,String>();
  final int prefixLength=sourceDirWithSeparator.length();
  for (  File f : files) {
    assert (f.exists() && f.isFile());
    if (f.getName().startsWith(".")) {
      continue;
    }
    final int extensionIndex=f.getName().lastIndexOf('.');
    final String filePath=f.getPath();
    if (extensionIndex < 0 || !filePath.startsWith(sourceDirWithSeparator)) {
      continue;
    }
    final int reverseExtensionIndex=f.getName().length() - extensionIndex;
    final String classPathName=filePath.substring(prefixLength,filePath.length() - reverseExtensionIndex);
    if (classPathName.contains(".")) {
      continue;
    }
    if (requiredExtension != null) {
      final String extension=f.getName().substring(extensionIndex + 1);
      if (!extension.equals(requiredExtension)) {
        continue;
      }
    }
    classFileNames.put(classPathName.replace(File.separator,"."),filePath);
  }
  return classFileNames;
}

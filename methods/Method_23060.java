private void copyRuntime(File plugInsDirectory) throws IOException {
  if (runtime != null) {
    File runtimeHomeDirectory=runtime.getDir();
    File runtimeContentsDirectory=runtimeHomeDirectory.getParentFile();
    File runtimeDirectory=runtimeContentsDirectory.getParentFile();
    File pluginDirectory=new File(plugInsDirectory,runtimeDirectory.getName());
    pluginDirectory.mkdir();
    File pluginContentsDirectory=new File(pluginDirectory,runtimeContentsDirectory.getName());
    pluginContentsDirectory.mkdir();
    File runtimeMacOSDirectory=new File(runtimeContentsDirectory,"MacOS");
    copy(runtimeMacOSDirectory,new File(pluginContentsDirectory,runtimeMacOSDirectory.getName()));
    File runtimeInfoPlistFile=new File(runtimeContentsDirectory,"Info.plist");
    copy(runtimeInfoPlistFile,new File(pluginContentsDirectory,runtimeInfoPlistFile.getName()));
    File pluginHomeDirectory=new File(pluginContentsDirectory,runtimeHomeDirectory.getName());
    DirectoryScanner directoryScanner=runtime.getDirectoryScanner(getProject());
    String[] includedFiles=directoryScanner.getIncludedFiles();
    for (    String includedFile : includedFiles) {
      File source=new File(runtimeHomeDirectory,includedFile);
      File destination=new File(pluginHomeDirectory,includedFile);
      copy(source,destination);
    }
  }
}

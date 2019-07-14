private void copyBundleIcons(File resourcesDirectory) throws IOException {
  for (  BundleDocument bundleDocument : bundleDocuments) {
    if (bundleDocument.hasIcon()) {
      File iconFile=bundleDocument.getIconFile();
      copy(iconFile,new File(resourcesDirectory,iconFile.getName()));
    }
  }
}

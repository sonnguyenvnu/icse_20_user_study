private void copyIcon(File resourcesDirectory) throws IOException {
  if (iconFile == null) {
    copy(getClass().getResource(DEFAULT_ICON_NAME),new File(resourcesDirectory,DEFAULT_ICON_NAME));
  }
 else {
    copy(iconFile,new File(resourcesDirectory,iconFile.getName()));
  }
}

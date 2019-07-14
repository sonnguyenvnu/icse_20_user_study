protected boolean matchFileExtension() throws IOException {
  String fileNameExtension=FileNameUtil.getExtension(getHeader().getFileName());
  for (  String fileExtension : fileExtensions) {
    if (fileNameExtension.equalsIgnoreCase(fileExtension)) {
      if (!allowFileExtensions) {
        if (breakOnError) {
          throw new IOException("Upload filename extension not allowed: " + fileNameExtension);
        }
        size=input.skipToBoundary();
        return false;
      }
      return true;
    }
  }
  if (allowFileExtensions) {
    if (breakOnError) {
      throw new IOException("Upload filename extension not allowed: " + fileNameExtension);
    }
    size=input.skipToBoundary();
    return false;
  }
  return true;
}

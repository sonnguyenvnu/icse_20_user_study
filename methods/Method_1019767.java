@Override public void extract(String imagePath,byte[] imageData) throws IOException {
  super.extract(getImageRelativePath(imagePath),imageData);
}

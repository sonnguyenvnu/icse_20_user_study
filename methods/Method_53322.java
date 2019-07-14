/** 
 * Check the existence, and the type of the specified file.
 * @param image image to be uploaded
 * @throws TwitterException when the specified file is not found (FileNotFoundException will be nested), or when the specified file object is not representing a file(IOException will be nested).
 */
private void checkFileValidity(File image) throws TwitterException {
  if (!image.exists()) {
    throw new TwitterException(new FileNotFoundException(image + " is not found."));
  }
  if (!image.isFile()) {
    throw new TwitterException(new IOException(image + " is not a file."));
  }
}

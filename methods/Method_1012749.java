/** 
 * A copy of  {@link ImageIO#read(InputStream)} that calls{ {@link #read(ImageInputStream)} instead of{@link ImageIO#read(ImageInputStream)} and that returns{@link ImageReaderResult} instead of {@link BufferedImage}. This lets information about the detected format be retained. <p><b> This method consumes and closes  {@code inputStream}. </b>
 * @param inputStream an {@link InputStream} to read from.
 * @see ImageIO#read(InputStream)
 */
public static ImageReaderResult read(InputStream inputStream) throws IOException {
  if (inputStream == null) {
    throw new IllegalArgumentException("input == null!");
  }
  ImageInputStream stream=createImageInputStream(inputStream);
  try {
    ImageReaderResult result=read(stream);
    if (result == null) {
      inputStream.close();
    }
    return result;
  }
 catch (  RuntimeException|IOException e) {
    try {
      inputStream.close();
    }
 catch (    Exception e2) {
    }
    if (e instanceof RuntimeException) {
      throw new ImageIORuntimeException("An error occurred while trying to read image: " + e.getMessage(),(RuntimeException)e);
    }
    throw e;
  }
}

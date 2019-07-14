/** 
 * Spew the contents of a String object out to a file. As of 3.0 beta 2, this will replace and write \r\n for newlines on Windows. https://github.com/processing/processing/issues/3455 As of 3.3.7, this puts a newline at the end of the file, per good practice/POSIX: https://stackoverflow.com/a/729795
 */
static public void saveFile(String text,File file) throws IOException {
  String[] lines=text.split("\\r?\\n");
  File temp=File.createTempFile(file.getName(),null,file.getParentFile());
  try {
    File canon=file.getCanonicalFile();
    file=canon;
  }
 catch (  IOException e) {
    throw new IOException("Could not resolve canonical representation of " + file.getAbsolutePath());
  }
  PrintWriter writer=PApplet.createWriter(temp);
  for (  String line : lines) {
    writer.println(line);
  }
  boolean error=writer.checkError();
  writer.close();
  if (error) {
    throw new IOException("Error while trying to save " + file);
  }
  if (file.exists()) {
    boolean result=file.delete();
    if (!result) {
      throw new IOException("Could not remove old version of " + file.getAbsolutePath());
    }
  }
  boolean result=temp.renameTo(file);
  if (!result) {
    throw new IOException("Could not replace " + file.getAbsolutePath() + " with " + temp.getAbsolutePath());
  }
}

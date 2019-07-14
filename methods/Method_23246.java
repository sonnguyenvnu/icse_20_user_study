/** 
 * Call openStream() without automatic gzip decompression.
 */
public InputStream createInputRaw(String filename){
  if (filename == null)   return null;
  if (sketchPath == null) {
    System.err.println("The sketch path is not set.");
    throw new RuntimeException("Files must be loaded inside setup() or after it has been called.");
  }
  if (filename.length() == 0) {
    return null;
  }
  if (filename.contains(":")) {
    try {
      URL url=new URL(filename);
      URLConnection conn=url.openConnection();
      if (conn instanceof HttpURLConnection) {
        HttpURLConnection httpConn=(HttpURLConnection)conn;
        httpConn.setInstanceFollowRedirects(true);
        int response=httpConn.getResponseCode();
        if (response >= 300 && response < 400) {
          String newLocation=httpConn.getHeaderField("Location");
          return createInputRaw(newLocation);
        }
        return conn.getInputStream();
      }
 else       if (conn instanceof JarURLConnection) {
        return url.openStream();
      }
    }
 catch (    MalformedURLException mfue) {
    }
catch (    FileNotFoundException fnfe) {
    }
catch (    IOException e) {
      printStackTrace(e);
      return null;
    }
  }
  InputStream stream=null;
  try {
    File file=new File(dataPath(filename));
    if (!file.exists()) {
      file=sketchFile(filename);
    }
    if (file.isDirectory()) {
      return null;
    }
    if (file.exists()) {
      try {
        String filePath=file.getCanonicalPath();
        String filenameActual=new File(filePath).getName();
        String filenameShort=new File(filename).getName();
        if (!filenameActual.equals(filenameShort)) {
          throw new RuntimeException("This file is named " + filenameActual + " not " + filename + ". Rename the file " + "or change your code.");
        }
      }
 catch (      IOException e) {
      }
    }
    stream=new FileInputStream(file);
    if (stream != null)     return stream;
  }
 catch (  IOException ioe) {
  }
catch (  SecurityException se) {
  }
  ClassLoader cl=getClass().getClassLoader();
  stream=cl.getResourceAsStream("data/" + filename);
  if (stream != null) {
    String cn=stream.getClass().getName();
    if (!cn.equals("sun.plugin.cache.EmptyInputStream")) {
      return stream;
    }
  }
  stream=cl.getResourceAsStream(filename);
  if (stream != null) {
    String cn=stream.getClass().getName();
    if (!cn.equals("sun.plugin.cache.EmptyInputStream")) {
      return stream;
    }
  }
  try {
    try {
      try {
        stream=new FileInputStream(dataPath(filename));
        if (stream != null)         return stream;
      }
 catch (      IOException e2) {
      }
      try {
        stream=new FileInputStream(sketchPath(filename));
        if (stream != null)         return stream;
      }
 catch (      Exception e) {
      }
      try {
        stream=new FileInputStream(filename);
        if (stream != null)         return stream;
      }
 catch (      IOException e1) {
      }
    }
 catch (    SecurityException se) {
    }
  }
 catch (  Exception e) {
    printStackTrace(e);
  }
  return null;
}

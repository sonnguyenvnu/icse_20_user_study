@SuppressWarnings("resource") private String resourceFilename(String filename){
  InputStream stream=null;
  try {
    File file=new File(sketch.dataPath(filename));
    if (!file.exists()) {
      file=sketch.sketchFile(filename);
    }
    if (file.exists() && !file.isDirectory()) {
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
    if (stream != null) {
      stream.close();
      return file.getCanonicalPath();
    }
  }
 catch (  IOException ioe) {
  }
catch (  SecurityException se) {
  }
  ClassLoader cl=sketch.getClass().getClassLoader();
  try {
    stream=cl.getResourceAsStream("data/" + filename);
    if (stream != null) {
      String cn=stream.getClass().getName();
      if (!cn.equals("sun.plugin.cache.EmptyInputStream")) {
        stream.close();
        return "data/" + filename;
      }
    }
    stream=cl.getResourceAsStream(filename);
    if (stream != null) {
      String cn=stream.getClass().getName();
      if (!cn.equals("sun.plugin.cache.EmptyInputStream")) {
        stream.close();
        return filename;
      }
    }
  }
 catch (  IOException e) {
  }
  try {
    try {
      try {
        String path=sketch.dataPath(filename);
        stream=new FileInputStream(path);
        if (stream != null) {
          stream.close();
          return path;
        }
      }
 catch (      IOException e2) {
      }
      try {
        String path=sketch.sketchPath(filename);
        stream=new FileInputStream(path);
        if (stream != null) {
          stream.close();
          return path;
        }
      }
 catch (      Exception e) {
      }
      try {
        stream=new FileInputStream(filename);
        if (stream != null) {
          stream.close();
          return filename;
        }
      }
 catch (      IOException e1) {
      }
    }
 catch (    SecurityException se) {
    }
  }
 catch (  Exception e) {
    e.printStackTrace();
  }
  return "";
}

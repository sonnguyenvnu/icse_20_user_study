private String readSource(File file){
  FileReader fileReader=null;
  BufferedReader reader=null;
  try {
    fileReader=new FileReader(file);
    reader=new BufferedReader(fileReader);
    String line=null;
    StringBuilder sb=new StringBuilder();
    do {
      line=reader.readLine();
      if (line != null) {
        sb.append(line).append("\n");
      }
    }
 while (line != null);
    reader.close();
    return sb.toString();
  }
 catch (  Exception e) {
    MarkdownWebServerPlugin.LOG.log(Level.SEVERE,"could not read source",e);
    return null;
  }
 finally {
    try {
      if (fileReader != null) {
        fileReader.close();
      }
      if (reader != null) {
        reader.close();
      }
    }
 catch (    IOException ignored) {
      MarkdownWebServerPlugin.LOG.log(Level.FINEST,"close failed",ignored);
    }
  }
}

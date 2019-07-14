private void readUrlFile() throws IOException {
  String line;
  BufferedReader fileUrlReader=null;
  try {
    fileUrlReader=new BufferedReader(new FileReader(getFileName(fileUrlAllName)));
    int lineReaded=0;
    while ((line=fileUrlReader.readLine()) != null) {
      urls.add(line.trim());
      lineReaded++;
      if (lineReaded > cursor.get()) {
        queue.add(new Request(line));
      }
    }
  }
  finally {
    if (fileUrlReader != null) {
      IOUtils.closeQuietly(fileUrlReader);
    }
  }
}

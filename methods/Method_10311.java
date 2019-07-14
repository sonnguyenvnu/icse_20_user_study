private void writeMetaData(OutputStream os,String name,String contentType) throws IOException {
  os.write(STREAM_NAME);
  os.write(':');
  os.write(escape(name));
  os.write(',');
  os.write(STREAM_TYPE);
  os.write(':');
  os.write(escape(contentType));
  os.write(',');
  os.write(STREAM_CONTENTS);
  os.write(':');
  os.write('"');
}

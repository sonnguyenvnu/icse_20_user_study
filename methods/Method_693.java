public int writeToEx(OutputStream out,Charset charset) throws IOException {
  if (this.writer != null) {
    throw new UnsupportedOperationException("writer not null");
  }
  if (charset == IOUtils.UTF8) {
    return encodeToUTF8(out);
  }
 else {
    byte[] bytes=new String(buf,0,count).getBytes(charset);
    out.write(bytes);
    return bytes.length;
  }
}

private static PrintStream filter(PrintStream out,final String[] exclude){
  return new PrintStream(new FilterOutputStream(out){
    @Override public void write(    byte[] b) throws IOException {
      write(b,0,b.length);
    }
    @Override public void write(    byte[] b,    int off,    int len) throws IOException {
      for (int i=off; i < len; i++) {
        write(b[i]);
      }
    }
    public void write(    byte b) throws IOException {
      buff.write(b);
      if (b == '\n') {
        byte[] data=buff.toByteArray();
        String line=new String(data,StandardCharsets.UTF_8);
        boolean print=true;
        for (        String l : exclude) {
          if (line.startsWith(l)) {
            print=false;
            break;
          }
        }
        if (print) {
          out.write(data);
        }
        buff.reset();
      }
    }
    @Override public void close() throws IOException {
      write('\n');
    }
  }
);
}

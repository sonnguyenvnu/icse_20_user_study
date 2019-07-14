private BufferedWriter getBufferedWriter(final Writer writer){
  final BufferedWriter bw;
  if (writer instanceof BufferedWriter) {
    bw=(BufferedWriter)writer;
  }
 else {
    bw=new BufferedWriter(writer);
  }
  return bw;
}

void flush_char(OutputStream outs) throws IOException {
  if (a_count > 0) {
    outs.write(a_count);
    outs.write(accum,0,a_count);
    a_count=0;
  }
}

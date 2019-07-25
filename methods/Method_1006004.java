@Benchmark public String write() throws Exception {
  return getOutputWriter().toString();
}

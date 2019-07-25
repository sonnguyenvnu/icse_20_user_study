public void init() throws IOException {
  System.setOut(createCompositeWrapper(myInputOut));
  System.setErr(createCompositeWrapper(myInputErr));
}

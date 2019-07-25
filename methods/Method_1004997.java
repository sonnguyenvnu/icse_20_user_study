public void load(final String csvString) throws IOException, OperationException {
  load(() -> new StringReader(csvString));
}

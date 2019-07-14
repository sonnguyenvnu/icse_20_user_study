private void outputMessage(final BufferedWriter outputWriter,final String msg) throws IOException {
  outputWriter.append(msg);
  outputWriter.flush();
}

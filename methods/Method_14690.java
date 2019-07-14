public void println() throws IOException {
  printIndent();
  m_writer.write("\n");
  m_indent=true;
}

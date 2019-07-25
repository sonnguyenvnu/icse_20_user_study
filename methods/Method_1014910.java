@Override public void write(int b) throws IOException {
  textArea.append(String.valueOf((char)b));
  textArea.setCaretPosition(textArea.getDocument().getLength());
}

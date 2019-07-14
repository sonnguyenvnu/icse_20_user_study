public static void writeComment(final Appendable appendable,final CharSequence comment) throws IOException {
  appendable.append("<!--");
  appendable.append(comment);
  appendable.append("-->");
}

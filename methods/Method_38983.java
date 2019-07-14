public static void writeCData(final Appendable appendable,final CharSequence value) throws IOException {
  appendable.append("<![CDATA[");
  appendable.append(value);
  appendable.append("]]>");
}

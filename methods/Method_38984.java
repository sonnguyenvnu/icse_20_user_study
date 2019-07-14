public static void writeDoctype(final Appendable appendable,final CharSequence name,final CharSequence publicIdentifier,final CharSequence systemIdentifier) throws IOException {
  appendable.append("<!DOCTYPE ");
  if (name != null) {
    appendable.append(name);
  }
  if (publicIdentifier != null) {
    appendable.append(" PUBLIC");
  }
 else   if (systemIdentifier != null) {
    appendable.append(" SYSTEM");
  }
  if (publicIdentifier != null) {
    appendable.append(" \"").append(publicIdentifier).append('"');
  }
  if (systemIdentifier != null) {
    appendable.append(" \"").append(systemIdentifier).append('"');
  }
  appendable.append('>');
}

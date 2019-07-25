/** 
 * Converts ordinal numbers to superscripts, e.g. 1st, 2nd or 3rd. Will replace ordinal numbers even if they are semantically wrong, e.g. 21rd <example> 1st Conf. Cloud Computing -> 1\textsuperscript{st} Conf. Cloud Computing </example>
 */
@Override public String format(String value){
  Objects.requireNonNull(value);
  if (value.isEmpty()) {
    return value;
  }
  Matcher matcher=SUPERSCRIPT_DETECT_PATTERN.matcher(value);
  return matcher.replaceAll(SUPERSCRIPT_REPLACE_PATTERN);
}

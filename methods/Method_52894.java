/** 
 * complete node literal
 * @param t
 * @return A node literal.
 */
public static String tokenLiteral(final Token t){
  if (t.kind == VmParserConstants.MULTI_LINE_COMMENT) {
    return "";
  }
 else   if (t.specialToken == null || t.specialToken.image.startsWith("##")) {
    return t.image;
  }
 else {
    final StrBuilder special=getSpecialText(t);
    if (special.length() > 0) {
      return special.append(t.image).toString();
    }
    return t.image;
  }
}

/** 
 * Generate comment text which  {@code exactMatch} would consider to match the formal parametername.
 */
static String toCommentText(String formal){
  return String.format("/* %s%s */",formal,PARAMETER_COMMENT_MARKER);
}

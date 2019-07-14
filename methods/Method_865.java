/** 
 * Common Situations, check in following order: 1. commented import 2. commented field 3. commented method 4. commented statement
 * @param content comment content
 * @return check result
 */
protected CommentPatternEnum scanCommentedCode(String content){
  CommentPatternEnum pattern=CommentPatternEnum.NONE;
  if (PRE_TAG_PATTERN.matcher(content).matches()) {
    return pattern;
  }
  if (IMPORT_PATTERN.matcher(content).matches()) {
    pattern=CommentPatternEnum.IMPORT;
  }
 else   if (FIELD_PATTERN.matcher(content).matches()) {
    pattern=CommentPatternEnum.FIELD;
  }
 else   if (METHOD_PATTERN.matcher(content).matches()) {
    pattern=CommentPatternEnum.METHOD;
  }
 else   if (STATEMENT_PATTERN.matcher(content).matches()) {
    pattern=CommentPatternEnum.STATEMENT;
  }
  return pattern;
}

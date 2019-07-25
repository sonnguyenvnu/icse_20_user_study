/** 
 * Configure the column namer.
 * @param stringValue the configuration
 */
public void configure(String stringValue){
  try {
    if (stringValue.equalsIgnoreCase(DEFAULT_COMMAND)) {
      configure(REGULAR);
    }
 else     if (stringValue.startsWith(EMULATE_COMMAND)) {
      configure(ModeEnum.valueOf(unquoteString(stringValue.substring(EMULATE_COMMAND.length()))));
    }
 else     if (stringValue.startsWith(MAX_IDENTIFIER_LENGTH)) {
      int maxLength=Integer.parseInt(stringValue.substring(MAX_IDENTIFIER_LENGTH.length()));
      setMaxIdentiferLength(maxLength);
    }
 else     if (stringValue.startsWith(GENERATE_UNIQUE_COLUMN_NAMES)) {
      setGenerateUniqueColumnNames(Integer.parseInt(stringValue.substring(GENERATE_UNIQUE_COLUMN_NAMES.length())) == 1);
    }
 else     if (stringValue.startsWith(DEFAULT_COLUMN_NAME_PATTERN)) {
      setDefaultColumnNamePattern(unquoteString(stringValue.substring(DEFAULT_COLUMN_NAME_PATTERN.length())));
    }
 else     if (stringValue.startsWith(REGULAR_EXPRESSION_MATCH_ALLOWED)) {
      setRegularExpressionMatchAllowed(unquoteString(stringValue.substring(REGULAR_EXPRESSION_MATCH_ALLOWED.length())));
    }
 else     if (stringValue.startsWith(REGULAR_EXPRESSION_MATCH_DISALLOWED)) {
      setRegularExpressionMatchDisallowed(unquoteString(stringValue.substring(REGULAR_EXPRESSION_MATCH_DISALLOWED.length())));
    }
 else {
      throw DbException.getInvalidValueException("SET COLUMN_NAME_RULES: unknown id:" + stringValue,stringValue);
    }
    recompilePatterns();
  }
 catch (  RuntimeException e) {
    throw DbException.getInvalidValueException("SET COLUMN_NAME_RULES:" + e.getMessage(),stringValue);
  }
}

/** 
 * Configure the rules.
 * @param modeEnum the mode
 */
public void configure(ModeEnum modeEnum){
switch (modeEnum) {
case Oracle:
    setMaxIdentiferLength(128);
  setRegularExpressionMatchAllowed("(?m)(?s)\"?[A-Za-z0-9_\\$#]+\"?");
setRegularExpressionMatchDisallowed("(?m)(?s)[^A-Za-z0-9_\"\\$#]");
setDefaultColumnNamePattern("_UNNAMED_$$");
setGenerateUniqueColumnNames(false);
break;
case MSSQLServer:
setMaxIdentiferLength(128);
setRegularExpressionMatchAllowed("(?m)(?s)[A-Za-z0-9_\\[\\]]+");
setRegularExpressionMatchDisallowed("(?m)(?s)[^A-Za-z0-9_\\[\\]]");
setDefaultColumnNamePattern("_UNNAMED_$$");
setGenerateUniqueColumnNames(false);
break;
case PostgreSQL:
setMaxIdentiferLength(63);
setRegularExpressionMatchAllowed("(?m)(?s)[A-Za-z0-9_\\$]+");
setRegularExpressionMatchDisallowed("(?m)(?s)[^A-Za-z0-9_\\$]");
setDefaultColumnNamePattern("_UNNAMED_$$");
setGenerateUniqueColumnNames(false);
break;
case MySQL:
setMaxIdentiferLength(64);
setRegularExpressionMatchAllowed("(?m)(?s)`?[A-Za-z0-9_`\\$]+`?");
setRegularExpressionMatchDisallowed("(?m)(?s)[^A-Za-z0-9_`\\$]");
setDefaultColumnNamePattern("_UNNAMED_$$");
setGenerateUniqueColumnNames(false);
break;
case REGULAR:
case DB2:
case Derby:
case HSQLDB:
case Ignite:
default :
setMaxIdentiferLength(Integer.MAX_VALUE);
setRegularExpressionMatchAllowed(null);
setRegularExpressionMatchDisallowed(null);
setDefaultColumnNamePattern("_UNNAMED_$$");
setGenerateUniqueColumnNames(false);
break;
}
recompilePatterns();
}

private boolean startsWithSafeResource(final ASTElExpression el){
  final ASTExpression expression=el.getFirstChildOfType(ASTExpression.class);
  if (expression != null) {
    final ASTNegationExpression negation=expression.getFirstChildOfType(ASTNegationExpression.class);
    if (negation != null) {
      return true;
    }
    final ASTIdentifier id=expression.getFirstChildOfType(ASTIdentifier.class);
    if (id != null) {
      String lowerCaseId=id.getImage().toLowerCase(Locale.ROOT);
      List<ASTArguments> args=expression.findChildrenOfType(ASTArguments.class);
      if (!args.isEmpty()) {
switch (lowerCaseId) {
case "urlfor":
case "casesafeid":
case "begins":
case "contains":
case "len":
case "getrecordids":
case "linkto":
case "sqrt":
case "round":
case "mod":
case "log":
case "ln":
case "exp":
case "abs":
case "floor":
case "ceiling":
case "nullvalue":
case "isnumber":
case "isnull":
case "isnew":
case "isblank":
case "isclone":
case "year":
case "month":
case "day":
case "datetimevalue":
case "datevalue":
case "date":
case "now":
case "today":
          return true;
default :
      }
    }
 else {
switch (lowerCaseId) {
case "$action":
case "$page":
case "$site":
case "$resource":
case "$label":
case "$objecttype":
case "$component":
case "$remoteaction":
        return true;
default :
    }
  }
}
}
return false;
}

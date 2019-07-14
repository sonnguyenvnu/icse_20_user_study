/** 
 * Tones down the jargon in the ecj reported errors.
 */
public static String getSimplifiedErrorMessage(IProblem iprob,String badCode){
  if (iprob == null)   return null;
  String args[]=iprob.getArguments();
  if (DEBUG) {
    Messages.log("Simplifying message: " + iprob.getMessage() + " ID: " + getIDName(iprob.getID()));
    Messages.log("Arg count: " + args.length);
    for (    String arg : args) {
      Messages.log("Arg " + arg);
    }
    Messages.log("Bad code: " + badCode);
  }
  String result=null;
switch (iprob.getID()) {
case IProblem.ParsingError:
    if (args.length > 0) {
      result=Language.interpolate("editor.status.error_on",args[0]);
    }
  break;
case IProblem.ParsingErrorDeleteToken:
if (args.length > 0) {
  if (args[0].equalsIgnoreCase("Invalid Character")) {
    result=getErrorMessageForCurlyQuote(badCode);
  }
}
break;
case IProblem.ParsingErrorDeleteTokens:
result=getErrorMessageForCurlyQuote(badCode);
if (result == null) {
result=Language.interpolate("editor.status.error_on",args[0]);
}
break;
case IProblem.ParsingErrorInsertToComplete:
if (args.length > 0) {
if (args[0].length() == 1) {
result=getErrorMessageForBracket(args[0].charAt(0));
}
 else {
if (args[0].equals("AssignmentOperator Expression")) {
result=Language.interpolate("editor.status.missing.add","=");
}
 else if (args[0].equalsIgnoreCase(") Statement")) {
result=getErrorMessageForBracket(args[0].charAt(0));
}
 else {
result=Language.interpolate("editor.status.error_on",args[0]);
}
}
}
break;
case IProblem.ParsingErrorInvalidToken:
if (args.length > 0) {
if (args[0].equals("int")) {
if (args[1].equals("VariableDeclaratorId")) {
result=Language.text("editor.status.reserved_words");
}
 else {
result=Language.interpolate("editor.status.error_on",args[0]);
}
}
 else if (args[0].equalsIgnoreCase("Invalid Character")) {
result=getErrorMessageForCurlyQuote(badCode);
}
if (result == null) {
result=Language.interpolate("editor.status.error_on",args[0]);
}
}
break;
case IProblem.ParsingErrorInsertTokenAfter:
if (args.length > 0) {
if (args[1].length() == 1) {
result=getErrorMessageForBracket(args[1].charAt(0));
}
 else {
if (args[1].equalsIgnoreCase("Statement")) {
result=Language.interpolate("editor.status.error_on",args[0]);
}
 else {
result=Language.interpolate("editor.status.error_on",args[0]) + " " + Language.interpolate("editor.status.missing.add",args[1]);
}
}
}
break;
case IProblem.ParsingErrorReplaceTokens:
result=getErrorMessageForCurlyQuote(badCode);
case IProblem.UndefinedConstructor:
if (args.length == 2) {
String constructorName=args[0];
if (constructorName.contains(".")) {
constructorName=constructorName.substring(constructorName.indexOf('.') + 1);
constructorName=constructorName.substring(constructorName.indexOf('.') + 1);
}
String constructorArgs=removePackagePrefixes(args[args.length - 1]);
result=Language.interpolate("editor.status.undefined_constructor",constructorName,constructorArgs);
}
break;
case IProblem.UndefinedMethod:
if (args.length > 2) {
String methodName=args[args.length - 2];
String methodArgs=removePackagePrefixes(args[args.length - 1]);
result=Language.interpolate("editor.status.undefined_method",methodName,methodArgs);
}
break;
case IProblem.ParameterMismatch:
if (args.length > 3) {
if (args[2].trim().length() == 0) {
result=Language.interpolate("editor.status.empty_param",args[1]);
}
 else {
result=Language.interpolate("editor.status.wrong_param",args[1],args[1],removePackagePrefixes(args[2]));
}
}
break;
case IProblem.UndefinedField:
if (args.length > 0) {
result=Language.interpolate("editor.status.undef_global_var",args[0]);
}
break;
case IProblem.UndefinedType:
if (args.length > 0) {
result=Language.interpolate("editor.status.undef_class",args[0]);
}
break;
case IProblem.UnresolvedVariable:
if (args.length > 0) {
result=Language.interpolate("editor.status.undef_var",args[0]);
}
break;
case IProblem.UndefinedName:
if (args.length > 0) {
result=Language.interpolate("editor.status.undef_name",args[0]);
}
break;
case IProblem.UnterminatedString:
if (badCode.contains("“") || badCode.contains("”")) {
result=Language.interpolate("editor.status.unterm_string_curly",badCode.replaceAll("[^“”]",""));
}
break;
case IProblem.TypeMismatch:
if (args.length > 1) {
result=Language.interpolate("editor.status.type_mismatch",args[0],args[1]);
}
break;
case IProblem.LocalVariableIsNeverUsed:
if (args.length > 0) {
result=Language.interpolate("editor.status.unused_variable",args[0]);
}
break;
case IProblem.UninitializedLocalVariable:
if (args.length > 0) {
result=Language.interpolate("editor.status.uninitialized_variable",args[0]);
}
break;
case IProblem.AssignmentHasNoEffect:
if (args.length > 0) {
result=Language.interpolate("editor.status.no_effect_assignment",args[0]);
}
break;
case IProblem.HidingEnclosingType:
if (args.length > 0) {
result=Language.interpolate("editor.status.hiding_enclosing_type",args[0]);
}
break;
}
if (result == null) {
String message=iprob.getMessage();
if (message != null) {
Matcher matcher=tokenRegExp.matcher(message);
message=matcher.replaceAll("");
result=message;
}
}
if (DEBUG) {
Messages.log("Simplified Error Msg: " + result);
}
return result;
}

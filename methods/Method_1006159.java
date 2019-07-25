@Override public String format(String oldString){
  String newValue=oldString;
  newValue=newValue.replace("$$","");
  newValue=REMOVE_REDUNDANT.matcher(newValue).replaceAll("$1");
  newValue=REPLACE_WITH_AT.matcher(newValue).replaceAll("$1@@");
  newValue=REPLACE_EVERY_OTHER_AT.matcher(newValue).replaceAll("$1\\$$2@@");
  newValue=MOVE_NUMBERS_WITH_OPERATORS.matcher(newValue).replaceAll("\\$$1");
  newValue=MOVE_NUMBERS_RIGHT_INTO_EQUATION.matcher(newValue).replaceAll(" $1@@");
  newValue=newValue.replace("@@","$");
  newValue=newValue.replace("  "," ");
  newValue=newValue.replace("$$","");
  newValue=newValue.replace(" )$",")$");
  newValue=ESCAPE_PERCENT_SIGN_ONCE.matcher(newValue).replaceAll("$1\\\\%");
  return newValue;
}

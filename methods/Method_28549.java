@NonNull private static String getCodeStyle(@NonNull Context context,boolean isDark){
  if (!isDark)   return "";
  String primaryColor=getCodeBackgroundColor(context);
  String accentColor="#" + Integer.toHexString(ViewHelper.getAccentColor(context)).substring(2).toUpperCase();
  return "<style>\n" + "body .highlight pre, body pre {\n" + "background-color: " + primaryColor + " !important;\n" + (PrefGetter.getThemeType(context) == PrefGetter.AMLOD ? "border: solid 1px " + accentColor + " !important;\n" : "") + "}\n" + "</style>";
}

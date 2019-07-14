static private String getErrorMessageForBracket(char c){
switch (c) {
case ';':
    return Language.text("editor.status.missing.semicolon");
case '[':
  return Language.text("editor.status.missing.left_sq_bracket");
case ']':
return Language.text("editor.status.missing.right_sq_bracket");
case '(':
return Language.text("editor.status.missing.left_paren");
case ')':
return Language.text("editor.status.missing.right_paren");
case '{':
return Language.text("editor.status.missing.left_curly_bracket");
case '}':
return Language.text("editor.status.missing.right_curly_bracket");
}
return Language.interpolate("editor.status.missing.default",c);
}

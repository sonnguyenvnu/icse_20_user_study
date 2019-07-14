public String getUserMessage(){
  String msg="";
  String desc=exception.getDescription();
switch (desc) {
case "Unclosed character class":
    msg="The regular expression is missing a closing ']' character, or has an empty pair of square brackets '[]'.";
  break;
case "Unmatched closing ')'":
msg="The regular expression is missing a opening '(' character.";
break;
case "Unclosed group":
msg="The regular expression is missing a closing ')' character.";
break;
case "Dangling meta character '*'":
case "Dangling meta character '+'":
case "Dangling meta character '?'":
msg="The regular expression has a '*','+' or '?' in the wrong place.";
break;
case "Unexpected internal error":
msg="The regular expression has a backslash '\\' at the end.";
break;
case "Unclosed counted closure":
msg="The regular expression is missing a closing '}' character, or has an incorrect quantifier statement in curly brackets '{}'.";
break;
case "Illegal repetition":
msg="The regular expression has an incomplete or incorrect quantifier statement in curly brackets '{}'.";
break;
case "Illegal repetition range":
msg="The regular expression has a quantifier statement where the minimum is larger than the maximum (e.g. {4,3}).";
break;
case "Illegal character range":
msg="The regular expression has a range statement which is incomplete or has the characters in the incorrect order (e.g. [9-0])";
break;
default :
msg=exception.getMessage();
break;
}
return msg;
}

private String computeAlternativeCloseQuote(char specialChar){
switch (specialChar) {
case '[':
    return "]'";
case '(':
  return ")'";
case '{':
return "}'";
case '<':
return ">'";
default :
return specialChar + "'";
}
}

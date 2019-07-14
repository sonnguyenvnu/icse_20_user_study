private static void applyEntity(String entity,SpannableStringBuilder spannedText){
switch (entity) {
case ENTITY_LESS_THAN:
    spannedText.append('<');
  break;
case ENTITY_GREATER_THAN:
spannedText.append('>');
break;
case ENTITY_NON_BREAK_SPACE:
spannedText.append(' ');
break;
case ENTITY_AMPERSAND:
spannedText.append('&');
break;
default :
Log.w(TAG,"ignoring unsupported entity: '&" + entity + ";'");
break;
}
}

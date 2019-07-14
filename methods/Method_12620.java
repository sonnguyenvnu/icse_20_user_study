/** 
 * ???????????
 * @return String
 */
public String getLexemeTypeString(){
switch (lexemeType) {
case TYPE_ENGLISH:
    return "ENGLISH";
case TYPE_ARABIC:
  return "ARABIC";
case TYPE_LETTER:
return "LETTER";
case TYPE_CNWORD:
return "CN_WORD";
case TYPE_CNCHAR:
return "CN_CHAR";
case TYPE_OTHER_CJK:
return "OTHER_CJK";
case TYPE_COUNT:
return "COUNT";
case TYPE_CNUM:
return "TYPE_CNUM";
case TYPE_CQUAN:
return "TYPE_CQUAN";
default :
return "UNKONW";
}
}

private String translateType(int type){
switch (type) {
case 0:
    return "unassigned";
case 1:
  return "uppercase letter";
case 2:
return "lowercase letter";
case 3:
return "titlecase letter";
case 4:
return "modifier letter";
case 5:
return "other letter";
case 6:
return "non spacing mark";
case 7:
return "enclosing mark";
case 8:
return "combining spacing mark";
case 9:
return "decimal digit number";
case 10:
return "letter number";
case 11:
return "other number";
case 12:
return "space separator";
case 13:
return "line separator";
case 14:
return "paragraph separator";
case 15:
return "control";
case 16:
return "format";
case 18:
return "private use";
case 19:
return "surrogate";
case 20:
return "dash punctuation";
case 21:
return "start punctuation";
case 22:
return "end punctuation";
case 23:
return "connector punctuation";
case 24:
return "other punctuation";
case 25:
return "math symbol";
case 26:
return "currency symbol";
case 27:
return "modifier symbol";
case 28:
return "other symbol";
case 29:
return "initial quote punctuation";
case 30:
return "final quote punctuation";
default :
return "unknown";
}
}

private String actionString(){
switch (action) {
default :
    return "UNKNOWN";
case CLICK:
  return "CLICK";
case DRAG:
return "DRAG";
case ENTER:
return "ENTER";
case EXIT:
return "EXIT";
case MOVE:
return "MOVE";
case PRESS:
return "PRESS";
case RELEASE:
return "RELEASE";
case WHEEL:
return "WHEEL";
}
}

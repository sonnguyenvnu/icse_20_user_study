private static String transformForModifiers(String start,int keymod,char lastChar){
  int modifier;
switch (keymod) {
case KEYMOD_SHIFT:
    modifier=2;
  break;
case KEYMOD_ALT:
modifier=3;
break;
case (KEYMOD_SHIFT | KEYMOD_ALT):
modifier=4;
break;
case KEYMOD_CTRL:
modifier=5;
break;
case KEYMOD_SHIFT | KEYMOD_CTRL:
modifier=6;
break;
case KEYMOD_ALT | KEYMOD_CTRL:
modifier=7;
break;
case KEYMOD_SHIFT | KEYMOD_ALT | KEYMOD_CTRL:
modifier=8;
break;
default :
return start + lastChar;
}
return start + (";" + modifier) + lastChar;
}

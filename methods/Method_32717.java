static char parseZoneChar(char c){
switch (c) {
case 's':
case 'S':
    return 's';
case 'u':
case 'U':
case 'g':
case 'G':
case 'z':
case 'Z':
  return 'u';
case 'w':
case 'W':
default :
return 'w';
}
}

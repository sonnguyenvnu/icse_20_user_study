private static long getNativeFunction(char type){
switch (type) {
case 'v':
    return VOID;
case 'B':
  return BOOLEAN;
case 'c':
return BYTE;
case 's':
return SHORT;
case 'i':
return INT;
case 'l':
return LONG;
case 'p':
return PTR;
case 'f':
return FLOAT;
case 'd':
return DOUBLE;
default :
throw new IllegalArgumentException();
}
}

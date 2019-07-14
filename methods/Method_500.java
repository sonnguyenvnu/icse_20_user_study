public final Number integerValue() throws NumberFormatException {
  long result=0;
  boolean negative=false;
  if (np == -1) {
    np=0;
  }
  int i=np, max=np + sp;
  long limit;
  long multmin;
  int digit;
  char type=' ';
switch (charAt(max - 1)) {
case 'L':
    max--;
  type='L';
break;
case 'S':
max--;
type='S';
break;
case 'B':
max--;
type='B';
break;
default :
break;
}
if (charAt(np) == '-') {
negative=true;
limit=Long.MIN_VALUE;
i++;
}
 else {
limit=-Long.MAX_VALUE;
}
multmin=MULTMIN_RADIX_TEN;
if (i < max) {
digit=charAt(i++) - '0';
result=-digit;
}
while (i < max) {
digit=charAt(i++) - '0';
if (result < multmin) {
return new BigInteger(numberString());
}
result*=10;
if (result < limit + digit) {
return new BigInteger(numberString());
}
result-=digit;
}
if (negative) {
if (i > np + 1) {
if (result >= Integer.MIN_VALUE && type != 'L') {
if (type == 'S') {
return (short)result;
}
if (type == 'B') {
return (byte)result;
}
return (int)result;
}
return result;
}
 else {
throw new NumberFormatException(numberString());
}
}
 else {
result=-result;
if (result <= Integer.MAX_VALUE && type != 'L') {
if (type == 'S') {
return (short)result;
}
if (type == 'B') {
return (byte)result;
}
return (int)result;
}
return result;
}
}

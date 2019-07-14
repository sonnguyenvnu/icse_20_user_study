private void _consumeNumber(final int unconsumeNdx){
  ndx++;
  if (isEOF()) {
    ndx=unconsumeNdx;
    return;
  }
  char c=input[ndx];
  int value=0;
  int digitCount=0;
  if (c == 'X' || c == 'x') {
    while (true) {
      ndx++;
      if (isEOF()) {
        ndx=unconsumeNdx;
        return;
      }
      c=input[ndx];
      if (isDigit(c)) {
        value*=16;
        value+=c - '0';
        digitCount++;
      }
 else       if ((c >= 'a') && (c <= 'f')) {
        value*=16;
        value+=c - 'a' + 10;
        digitCount++;
      }
 else       if ((c >= 'A') && (c <= 'F')) {
        value*=16;
        value+=c - 'A' + 10;
        digitCount++;
      }
 else {
        break;
      }
    }
  }
 else {
    while (isDigit(c)) {
      value*=10;
      value+=c - '0';
      ndx++;
      if (isEOF()) {
        ndx=unconsumeNdx;
        return;
      }
      c=input[ndx];
      digitCount++;
    }
  }
  if (digitCount == 0) {
    errorCharReference();
    ndx=unconsumeNdx;
    return;
  }
  if (c != ';') {
    errorCharReference();
    ndx--;
  }
  boolean isErr=true;
switch (value) {
case 0:
    c=REPLACEMENT_CHAR;
  break;
case 0x80:
c='\u20AC';
break;
case 0x81:
c='\u0081';
break;
case 0x82:
c='\u201A';
break;
case 0x83:
c='\u0192';
break;
case 0x84:
c='\u201E';
break;
case 0x85:
c='\u2026';
break;
case 0x86:
c='\u2020';
break;
case 0x87:
c='\u2021';
break;
case 0x88:
c='\u02C6';
break;
case 0x89:
c='\u2030';
break;
case 0x8A:
c='\u0160';
break;
case 0x8B:
c='\u2039';
break;
case 0x8C:
c='\u0152';
break;
case 0x8D:
c='\u008D';
break;
case 0x8E:
c='\u017D';
break;
case 0x8F:
c='\u008F';
break;
case 0x90:
c='\u0090';
break;
case 0x91:
c='\u2018';
break;
case 0x92:
c='\u2019';
break;
case 0x93:
c='\u201C';
break;
case 0x94:
c='\u201D';
break;
case 0x95:
c='\u2022';
break;
case 0x96:
c='\u2013';
break;
case 0x97:
c='\u2014';
break;
case 0x98:
c='\u02DC';
break;
case 0x99:
c='\u2122';
break;
case 0x9A:
c='\u0161';
break;
case 0x9B:
c='\u203A';
break;
case 0x9C:
c='\u0153';
break;
case 0x9D:
c='\u009D';
break;
case 0x9E:
c='\u017E';
break;
case 0x9F:
c='\u0178';
break;
default :
isErr=false;
}
if (isErr) {
errorCharReference();
textEmitChar(c);
return;
}
if (((value >= 0xD800) && (value <= 0xDFF)) || (value > 0x10FFFF)) {
errorCharReference();
textEmitChar(REPLACEMENT_CHAR);
return;
}
c=(char)value;
textEmitChar(c);
if (((c >= 0x0001) && (c <= 0x0008)) || ((c >= 0x000D) && (c <= 0x001F)) || ((c >= 0x007F) && (c <= 0x009F)) || ((c >= 0xFDD0) && (c <= 0xFDEF))) {
errorCharReference();
return;
}
if (equalsOne(c,INVALID_CHARS)) {
errorCharReference();
}
}

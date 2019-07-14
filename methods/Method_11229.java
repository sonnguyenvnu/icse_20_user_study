private String makeCode(){
  StringBuilder buffer=new StringBuilder();
switch (type) {
case NUMBER:
    for (int i=0; i < codeLength; i++) {
      buffer.append(CHARS_NUMBER[random.nextInt(CHARS_NUMBER.length)]);
    }
  break;
case LETTER:
for (int i=0; i < codeLength; i++) {
  buffer.append(CHARS_LETTER[random.nextInt(CHARS_LETTER.length)]);
}
break;
case CHARS:
for (int i=0; i < codeLength; i++) {
buffer.append(CHARS_ALL[random.nextInt(CHARS_ALL.length)]);
}
break;
default :
for (int i=0; i < codeLength; i++) {
buffer.append(CHARS_ALL[random.nextInt(CHARS_ALL.length)]);
}
break;
}
return buffer.toString();
}

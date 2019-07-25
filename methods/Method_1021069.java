private static Object parse(final StringTokenizer tokenizer){
  final char value=tokenizer.nextUnskippable();
switch (value) {
case '{':
    return readObjectTillTheEnd(tokenizer);
case '[':
  return readArrayTillTheEnd(tokenizer);
case '"':
return readStringTillTheEnd(tokenizer);
case 'n':
return readNullTillTheEnd(tokenizer);
case 't':
return readTrueTillTheEnd(tokenizer);
case 'f':
return readFalseTillTheEnd(tokenizer);
default :
return readNumberTillTheEnd(value,tokenizer);
}
}

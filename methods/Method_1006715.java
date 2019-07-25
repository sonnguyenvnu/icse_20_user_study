/** 
 * Parse a base type.
 * @param parser the parser
 * @return the base type signature
 */
static BaseTypeSignature parse(final Parser parser){
switch (parser.peek()) {
case 'B':
    parser.next();
  return BYTE;
case 'C':
parser.next();
return CHAR;
case 'D':
parser.next();
return DOUBLE;
case 'F':
parser.next();
return FLOAT;
case 'I':
parser.next();
return INT;
case 'J':
parser.next();
return LONG;
case 'S':
parser.next();
return SHORT;
case 'Z':
parser.next();
return BOOLEAN;
case 'V':
parser.next();
return VOID;
default :
return null;
}
}

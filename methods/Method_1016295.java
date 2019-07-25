/** 
 * Parses the given data buffer from the offset up to the specified number of characters.
 * @param data the buffer
 * @param offset the initial index
 * @param length the specified number of characters to parse.
 * @throws SerializationException if the buffer cannot be successfully parsed. 
 */
public void parse(char[] data,int offset,int length){
  int cs, p=offset, pe=length, eof=pe;
  int s=0;
  int indent=0;
  int taskIndex=-1;
  boolean isGuard=false;
  boolean isSubtreeRef=false;
  String statementName=null;
  boolean taskProcessed=false;
  boolean needsUnescape=false;
  boolean stringIsUnquoted=false;
  RuntimeException parseRuntimeEx=null;
  String attrName=null;
  lineNumber=1;
  try {
{
      cs=btree_start;
    }
{
      int _klen;
      int _trans=0;
      int _acts;
      int _nacts;
      int _keys;
      int _goto_targ=0;
      _goto:       while (true) {
switch (_goto_targ) {
case 0:
          if (p == pe) {
            _goto_targ=4;
            continue _goto;
          }
        if (cs == 0) {
          _goto_targ=5;
          continue _goto;
        }
case 1:
      _match:       do {
        _keys=_btree_key_offsets[cs];
        _trans=_btree_index_offsets[cs];
        _klen=_btree_single_lengths[cs];
        if (_klen > 0) {
          int _lower=_keys;
          int _mid;
          int _upper=_keys + _klen - 1;
          while (true) {
            if (_upper < _lower)             break;
            _mid=_lower + ((_upper - _lower) >> 1);
            if (data[p] < _btree_trans_keys[_mid])             _upper=_mid - 1;
 else             if (data[p] > _btree_trans_keys[_mid])             _lower=_mid + 1;
 else {
              _trans+=(_mid - _keys);
              break _match;
            }
          }
          _keys+=_klen;
          _trans+=_klen;
        }
        _klen=_btree_range_lengths[cs];
        if (_klen > 0) {
          int _lower=_keys;
          int _mid;
          int _upper=_keys + (_klen << 1) - 2;
          while (true) {
            if (_upper < _lower)             break;
            _mid=_lower + (((_upper - _lower) >> 1) & ~1);
            if (data[p] < _btree_trans_keys[_mid])             _upper=_mid - 2;
 else             if (data[p] > _btree_trans_keys[_mid + 1])             _lower=_mid + 2;
 else {
              _trans+=((_mid - _keys) >> 1);
              break _match;
            }
          }
          _trans+=_klen;
        }
      }
 while (false);
    _trans=_btree_indicies[_trans];
  cs=_btree_trans_targs[_trans];
if (_btree_trans_actions[_trans] != 0) {
  _acts=_btree_trans_actions[_trans];
  _nacts=(int)_btree_actions[_acts++];
  while (_nacts-- > 0) {
switch (_btree_actions[_acts++]) {
case 0:
{
        String value=new String(data,s,p - s);
        s=p;
        if (needsUnescape)         value=unescape(value);
        outer:         if (stringIsUnquoted) {
          if (debug)           GdxAI.getLogger().info(LOG_TAG,"string: " + attrName + "=" + value);
          if (value.equals("true")) {
            if (debug)             GdxAI.getLogger().info(LOG_TAG,"boolean: " + attrName + "=true");
            attribute(attrName,Boolean.TRUE);
            break outer;
          }
 else           if (value.equals("false")) {
            if (debug)             GdxAI.getLogger().info(LOG_TAG,"boolean: " + attrName + "=false");
            attribute(attrName,Boolean.FALSE);
            break outer;
          }
 else           if (value.equals("null")) {
            attribute(attrName,null);
            break outer;
          }
 else {
            try {
              if (containsFloatingPointCharacters(value)) {
                if (debug)                 GdxAI.getLogger().info(LOG_TAG,"double: " + attrName + "=" + Double.parseDouble(value));
                attribute(attrName,new Double(value));
                break outer;
              }
 else {
                if (debug)                 GdxAI.getLogger().info(LOG_TAG,"double: " + attrName + "=" + Double.parseDouble(value));
                attribute(attrName,new Long(value));
                break outer;
              }
            }
 catch (            NumberFormatException nfe) {
              throw new GdxRuntimeException("Attribute value must be a number, a boolean, a string or null");
            }
          }
        }
 else {
          if (debug)           GdxAI.getLogger().info(LOG_TAG,"string: " + attrName + "=\"" + value + "\"");
          attribute(attrName,value);
        }
        stringIsUnquoted=false;
      }
    break;
case 1:
{
    if (debug)     GdxAI.getLogger().info(LOG_TAG,"unquotedChars");
    s=p;
    needsUnescape=false;
    stringIsUnquoted=true;
    outer:     while (true) {
switch (data[p]) {
case '\\':
        needsUnescape=true;
      break;
case ')':
case '(':
case ' ':
case '\r':
case '\n':
case '\t':
    break outer;
}
p++;
if (p == eof) break;
}
p--;
}
break;
case 2:
{
if (debug) GdxAI.getLogger().info(LOG_TAG,"quotedChars");
s=++p;
needsUnescape=false;
outer: while (true) {
switch (data[p]) {
case '\\':
needsUnescape=true;
p++;
break;
case '"':
break outer;
}
p++;
if (p == eof) break;
}
p--;
}
break;
case 3:
{
indent=0;
taskIndex=-1;
isGuard=false;
isSubtreeRef=false;
statementName=null;
taskProcessed=false;
lineNumber++;
if (debug) GdxAI.getLogger().info(LOG_TAG,"****NEWLINE**** " + lineNumber);
}
break;
case 4:
{
indent++;
}
break;
case 5:
{
if (taskIndex >= 0) {
endStatement();
}
taskProcessed=true;
if (statementName != null) endLine();
if (debug) GdxAI.getLogger().info(LOG_TAG,"endLine: indent: " + indent + " taskName: " + statementName + " data[" + p + "] = " + (p >= eof ? "EOF" : "\"" + data[p] + "\""));
}
break;
case 6:
{
s=p;
}
break;
case 7:
{
if (reportsComments) {
comment(new String(data,s,p - s));
}
 else {
if (debug) GdxAI.getLogger().info(LOG_TAG,"# Comment");
}
}
break;
case 8:
{
if (taskIndex++ < 0) {
startLine(indent);
}
 else {
endStatement();
}
statementName=new String(data,s,p - s);
startStatement(statementName,isSubtreeRef,isGuard);
isGuard=false;
}
break;
case 9:
{
attrName=new String(data,s,p - s);
}
break;
case 10:
{
isSubtreeRef=false;
}
break;
case 11:
{
isSubtreeRef=true;
}
break;
case 12:
{
isGuard=true;
}
break;
case 13:
{
isGuard=false;
}
break;
}
}
}
case 2:
if (cs == 0) {
_goto_targ=5;
continue _goto;
}
if (++p != pe) {
_goto_targ=1;
continue _goto;
}
case 4:
if (p == eof) {
int __acts=_btree_eof_actions[cs];
int __nacts=(int)_btree_actions[__acts++];
while (__nacts-- > 0) {
switch (_btree_actions[__acts++]) {
case 0:
{
String value=new String(data,s,p - s);
s=p;
if (needsUnescape) value=unescape(value);
outer: if (stringIsUnquoted) {
if (debug) GdxAI.getLogger().info(LOG_TAG,"string: " + attrName + "=" + value);
if (value.equals("true")) {
if (debug) GdxAI.getLogger().info(LOG_TAG,"boolean: " + attrName + "=true");
attribute(attrName,Boolean.TRUE);
break outer;
}
 else if (value.equals("false")) {
if (debug) GdxAI.getLogger().info(LOG_TAG,"boolean: " + attrName + "=false");
attribute(attrName,Boolean.FALSE);
break outer;
}
 else if (value.equals("null")) {
attribute(attrName,null);
break outer;
}
 else {
try {
if (containsFloatingPointCharacters(value)) {
if (debug) GdxAI.getLogger().info(LOG_TAG,"double: " + attrName + "=" + Double.parseDouble(value));
attribute(attrName,new Double(value));
break outer;
}
 else {
if (debug) GdxAI.getLogger().info(LOG_TAG,"double: " + attrName + "=" + Double.parseDouble(value));
attribute(attrName,new Long(value));
break outer;
}
}
 catch (NumberFormatException nfe) {
throw new GdxRuntimeException("Attribute value must be a number, a boolean, a string or null");
}
}
}
 else {
if (debug) GdxAI.getLogger().info(LOG_TAG,"string: " + attrName + "=\"" + value + "\"");
attribute(attrName,value);
}
stringIsUnquoted=false;
}
break;
case 5:
{
if (taskIndex >= 0) {
endStatement();
}
taskProcessed=true;
if (statementName != null) endLine();
if (debug) GdxAI.getLogger().info(LOG_TAG,"endLine: indent: " + indent + " taskName: " + statementName + " data[" + p + "] = " + (p >= eof ? "EOF" : "\"" + data[p] + "\""));
}
break;
case 6:
{
s=p;
}
break;
case 7:
{
if (reportsComments) {
comment(new String(data,s,p - s));
}
 else {
if (debug) GdxAI.getLogger().info(LOG_TAG,"# Comment");
}
}
break;
case 8:
{
if (taskIndex++ < 0) {
startLine(indent);
}
 else {
endStatement();
}
statementName=new String(data,s,p - s);
startStatement(statementName,isSubtreeRef,isGuard);
isGuard=false;
}
break;
case 10:
{
isSubtreeRef=false;
}
break;
case 11:
{
isSubtreeRef=true;
}
break;
}
}
}
case 5:
}
break;
}
}
}
 catch (RuntimeException ex) {
parseRuntimeEx=ex;
}
if (p < pe || (statementName != null && !taskProcessed)) {
throw new SerializationException("Error parsing behavior tree on line " + lineNumber + " near: " + new String(data,p,pe - p),parseRuntimeEx);
}
 else if (parseRuntimeEx != null) {
throw new SerializationException("Error parsing behavior tree: " + new String(data),parseRuntimeEx);
}
}

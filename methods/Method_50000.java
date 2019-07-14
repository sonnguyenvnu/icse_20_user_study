/** 
 * Parse content type parameters. For now we just support four parameters used in mms: "type", "start", "name", "charset".
 * @param pduDataStream pdu data input stream
 * @param map to store parameters of Content-Type field
 * @param length length of all the parameters
 */
protected static void parseContentTypeParams(ByteArrayInputStream pduDataStream,HashMap<Integer,Object> map,Integer length){
  assert (null != pduDataStream);
  assert (length > 0);
  int startPos=pduDataStream.available();
  int tempPos=0;
  int lastLen=length;
  while (0 < lastLen) {
    int param=pduDataStream.read();
    assert (-1 != param);
    lastLen--;
switch (param) {
case PduPart.P_TYPE:
case PduPart.P_CT_MR_TYPE:
      pduDataStream.mark(1);
    int first=extractByteValue(pduDataStream);
  pduDataStream.reset();
if (first > TEXT_MAX) {
  int index=parseShortInteger(pduDataStream);
  if (index < PduContentTypes.contentTypes.length) {
    byte[] type=(PduContentTypes.contentTypes[index]).getBytes();
    map.put(PduPart.P_TYPE,type);
  }
 else {
  }
}
 else {
  byte[] type=parseWapString(pduDataStream,TYPE_TEXT_STRING);
  if ((null != type) && (null != map)) {
    map.put(PduPart.P_TYPE,type);
  }
}
tempPos=pduDataStream.available();
lastLen=length - (startPos - tempPos);
break;
case PduPart.P_START:
case PduPart.P_DEP_START:
byte[] start=parseWapString(pduDataStream,TYPE_TEXT_STRING);
if ((null != start) && (null != map)) {
map.put(PduPart.P_START,start);
}
tempPos=pduDataStream.available();
lastLen=length - (startPos - tempPos);
break;
case PduPart.P_CHARSET:
pduDataStream.mark(1);
int firstValue=extractByteValue(pduDataStream);
pduDataStream.reset();
if (((firstValue > TEXT_MIN) && (firstValue < TEXT_MAX)) || (END_STRING_FLAG == firstValue)) {
byte[] charsetStr=parseWapString(pduDataStream,TYPE_TEXT_STRING);
try {
int charsetInt=CharacterSets.getMibEnumValue(new String(charsetStr));
map.put(PduPart.P_CHARSET,charsetInt);
}
 catch (UnsupportedEncodingException e) {
Timber.e(e,Arrays.toString(charsetStr));
map.put(PduPart.P_CHARSET,CharacterSets.ANY_CHARSET);
}
}
 else {
int charset=(int)parseIntegerValue(pduDataStream);
if (map != null) {
map.put(PduPart.P_CHARSET,charset);
}
}
tempPos=pduDataStream.available();
lastLen=length - (startPos - tempPos);
break;
case PduPart.P_DEP_NAME:
case PduPart.P_NAME:
byte[] name=parseWapString(pduDataStream,TYPE_TEXT_STRING);
if ((null != name) && (null != map)) {
map.put(PduPart.P_NAME,name);
}
tempPos=pduDataStream.available();
lastLen=length - (startPos - tempPos);
break;
default :
if (LOCAL_LOGV) {
Timber.v("Not supported Content-Type parameter");
}
if (-1 == skipWapValue(pduDataStream,lastLen)) {
Timber.e("Corrupt Content-Type");
}
 else {
lastLen=0;
}
break;
}
}
if (0 != lastLen) {
Timber.e("Corrupt Content-Type");
}
}

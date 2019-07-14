/** 
 * Parse part's headers.
 * @param pduDataStream pdu data input stream
 * @param part to store the header informations of the part
 * @param length length of the headers
 * @return true if parse successfully, false otherwise
 */
protected boolean parsePartHeaders(ByteArrayInputStream pduDataStream,PduPart part,int length){
  assert (null != pduDataStream);
  assert (null != part);
  assert (length > 0);
  int startPos=pduDataStream.available();
  int tempPos=0;
  int lastLen=length;
  while (0 < lastLen) {
    int header=pduDataStream.read();
    assert (-1 != header);
    lastLen--;
    if (header > TEXT_MAX) {
switch (header) {
case PduPart.P_CONTENT_LOCATION:
        byte[] contentLocation=parseWapString(pduDataStream,TYPE_TEXT_STRING);
      if (null != contentLocation) {
        part.setContentLocation(contentLocation);
      }
    tempPos=pduDataStream.available();
  lastLen=length - (startPos - tempPos);
break;
case PduPart.P_CONTENT_ID:
byte[] contentId=parseWapString(pduDataStream,TYPE_QUOTED_STRING);
if (null != contentId) {
part.setContentId(contentId);
}
tempPos=pduDataStream.available();
lastLen=length - (startPos - tempPos);
break;
case PduPart.P_DEP_CONTENT_DISPOSITION:
case PduPart.P_CONTENT_DISPOSITION:
if (mParseContentDisposition) {
int len=parseValueLength(pduDataStream);
pduDataStream.mark(1);
int thisStartPos=pduDataStream.available();
int thisEndPos=0;
int value=pduDataStream.read();
if (value == PduPart.P_DISPOSITION_FROM_DATA) {
part.setContentDisposition(PduPart.DISPOSITION_FROM_DATA);
}
 else if (value == PduPart.P_DISPOSITION_ATTACHMENT) {
part.setContentDisposition(PduPart.DISPOSITION_ATTACHMENT);
}
 else if (value == PduPart.P_DISPOSITION_INLINE) {
part.setContentDisposition(PduPart.DISPOSITION_INLINE);
}
 else {
pduDataStream.reset();
part.setContentDisposition(parseWapString(pduDataStream,TYPE_TEXT_STRING));
}
thisEndPos=pduDataStream.available();
if (thisStartPos - thisEndPos < len) {
value=pduDataStream.read();
if (value == PduPart.P_FILENAME) {
part.setFilename(parseWapString(pduDataStream,TYPE_TEXT_STRING));
}
thisEndPos=pduDataStream.available();
if (thisStartPos - thisEndPos < len) {
int last=len - (thisStartPos - thisEndPos);
byte[] temp=new byte[last];
pduDataStream.read(temp,0,last);
}
}
tempPos=pduDataStream.available();
lastLen=length - (startPos - tempPos);
}
break;
default :
if (LOCAL_LOGV) {
Timber.v("Not supported Part headers: " + header);
}
if (-1 == skipWapValue(pduDataStream,lastLen)) {
Timber.e("Corrupt Part headers");
return false;
}
lastLen=0;
break;
}
}
 else if ((header >= TEXT_MIN) && (header <= TEXT_MAX)) {
byte[] tempHeader=parseWapString(pduDataStream,TYPE_TEXT_STRING);
byte[] tempValue=parseWapString(pduDataStream,TYPE_TEXT_STRING);
if (true == PduPart.CONTENT_TRANSFER_ENCODING.equalsIgnoreCase(new String(tempHeader))) {
part.setContentTransferEncoding(tempValue);
}
tempPos=pduDataStream.available();
lastLen=length - (startPos - tempPos);
}
 else {
if (LOCAL_LOGV) {
Timber.v("Not supported Part headers: " + header);
}
if (-1 == skipWapValue(pduDataStream,lastLen)) {
Timber.e("Corrupt Part headers");
return false;
}
lastLen=0;
}
}
if (0 != lastLen) {
Timber.e("Corrupt Part headers");
return false;
}
return true;
}

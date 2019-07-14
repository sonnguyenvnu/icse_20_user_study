/** 
 * Make message body.
 */
private int makeMessageBody(){
  mStack.newbuf();
  PositionMarker ctStart=mStack.mark();
  String contentType=new String(mPduHeader.getTextString(PduHeaders.CONTENT_TYPE));
  Integer contentTypeIdentifier=mContentTypeMap.get(contentType);
  if (contentTypeIdentifier == null) {
    return PDU_COMPOSE_CONTENT_ERROR;
  }
  appendShortInteger(contentTypeIdentifier.intValue());
  PduBody body=((SendReq)mPdu).getBody();
  if (null == body || body.getPartsNum() == 0) {
    appendUintvarInteger(0);
    mStack.pop();
    mStack.copy();
    return PDU_COMPOSE_SUCCESS;
  }
  PduPart part;
  try {
    part=body.getPart(0);
    byte[] start=part.getContentId();
    if (start != null) {
      appendOctet(PduPart.P_DEP_START);
      if (('<' == start[0]) && ('>' == start[start.length - 1])) {
        appendTextString(start);
      }
 else {
        appendTextString("<" + new String(start) + ">");
      }
    }
    appendOctet(PduPart.P_CT_MR_TYPE);
    appendTextString(part.getContentType());
  }
 catch (  ArrayIndexOutOfBoundsException e) {
    Timber.e(e,"logging error");
    e.printStackTrace();
  }
  int ctLength=ctStart.getLength();
  mStack.pop();
  appendValueLength(ctLength);
  mStack.copy();
  int partNum=body.getPartsNum();
  appendUintvarInteger(partNum);
  for (int i=0; i < partNum; i++) {
    part=body.getPart(i);
    mStack.newbuf();
    PositionMarker attachment=mStack.mark();
    mStack.newbuf();
    PositionMarker contentTypeBegin=mStack.mark();
    byte[] partContentType=part.getContentType();
    if (partContentType == null) {
      return PDU_COMPOSE_CONTENT_ERROR;
    }
    Integer partContentTypeIdentifier=mContentTypeMap.get(new String(partContentType));
    if (partContentTypeIdentifier == null) {
      appendTextString(partContentType);
    }
 else {
      appendShortInteger(partContentTypeIdentifier.intValue());
    }
    byte[] name=part.getName();
    if (null == name) {
      name=part.getFilename();
      if (null == name) {
        name=part.getContentLocation();
        if (null == name) {
          return PDU_COMPOSE_CONTENT_ERROR;
        }
      }
    }
    appendOctet(PduPart.P_DEP_NAME);
    appendTextString(name);
    int charset=part.getCharset();
    if (charset != 0) {
      appendOctet(PduPart.P_CHARSET);
      appendShortInteger(charset);
    }
    int contentTypeLength=contentTypeBegin.getLength();
    mStack.pop();
    appendValueLength(contentTypeLength);
    mStack.copy();
    byte[] contentId=part.getContentId();
    if (null != contentId) {
      appendOctet(PduPart.P_CONTENT_ID);
      if (('<' == contentId[0]) && ('>' == contentId[contentId.length - 1])) {
        appendQuotedString(contentId);
      }
 else {
        appendQuotedString("<" + new String(contentId) + ">");
      }
    }
    byte[] contentLocation=part.getContentLocation();
    if (null != contentLocation) {
      appendOctet(PduPart.P_CONTENT_LOCATION);
      appendTextString(contentLocation);
    }
    int headerLength=attachment.getLength();
    int dataLength=0;
    byte[] partData=part.getData();
    if (partData != null) {
      arraycopy(partData,0,partData.length);
      dataLength=partData.length;
    }
 else {
      InputStream cr=null;
      try {
        byte[] buffer=new byte[PDU_COMPOSER_BLOCK_SIZE];
        cr=mResolver.openInputStream(part.getDataUri());
        int len=0;
        while ((len=cr.read(buffer)) != -1) {
          mMessage.write(buffer,0,len);
          mPosition+=len;
          dataLength+=len;
        }
      }
 catch (      FileNotFoundException e) {
        return PDU_COMPOSE_CONTENT_ERROR;
      }
catch (      IOException e) {
        return PDU_COMPOSE_CONTENT_ERROR;
      }
catch (      RuntimeException e) {
        return PDU_COMPOSE_CONTENT_ERROR;
      }
 finally {
        if (cr != null) {
          try {
            cr.close();
          }
 catch (          IOException e) {
          }
        }
      }
    }
    if (dataLength != (attachment.getLength() - headerLength)) {
      throw new RuntimeException("BUG: Length sanity check failed");
    }
    mStack.pop();
    appendUintvarInteger(headerLength);
    appendUintvarInteger(dataLength);
    mStack.copy();
  }
  return PDU_COMPOSE_SUCCESS;
}

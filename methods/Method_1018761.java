/** 
 * Negotiates prelogin information with the server.
 */
void Prelogin(String serverName,int portNumber) throws SQLServerException {
  if ((!authenticationString.equalsIgnoreCase(SqlAuthentication.NotSpecified.toString())) || (null != accessTokenInByte)) {
    fedAuthRequiredByUser=true;
  }
  final byte messageLength;
  final byte fedAuthOffset;
  if (fedAuthRequiredByUser) {
    messageLength=TDS.B_PRELOGIN_MESSAGE_LENGTH_WITH_FEDAUTH;
    requestedEncryptionLevel=TDS.ENCRYPT_ON;
    fedAuthOffset=5;
  }
 else {
    messageLength=TDS.B_PRELOGIN_MESSAGE_LENGTH;
    fedAuthOffset=0;
  }
  final byte[] preloginRequest=new byte[messageLength];
  int preloginRequestOffset=0;
  byte[] bufferHeader={TDS.PKT_PRELOGIN,TDS.STATUS_BIT_EOM,0,messageLength,0,0,0,0};
  System.arraycopy(bufferHeader,0,preloginRequest,preloginRequestOffset,bufferHeader.length);
  preloginRequestOffset=preloginRequestOffset + bufferHeader.length;
  byte[] preloginOptionsBeforeFedAuth={TDS.B_PRELOGIN_OPTION_VERSION,0,(byte)(16 + fedAuthOffset),0,6,TDS.B_PRELOGIN_OPTION_ENCRYPTION,0,(byte)(22 + fedAuthOffset),0,1,TDS.B_PRELOGIN_OPTION_TRACEID,0,(byte)(23 + fedAuthOffset),0,36};
  System.arraycopy(preloginOptionsBeforeFedAuth,0,preloginRequest,preloginRequestOffset,preloginOptionsBeforeFedAuth.length);
  preloginRequestOffset=preloginRequestOffset + preloginOptionsBeforeFedAuth.length;
  if (fedAuthRequiredByUser) {
    byte[] preloginOptions2={TDS.B_PRELOGIN_OPTION_FEDAUTHREQUIRED,0,64,0,1};
    System.arraycopy(preloginOptions2,0,preloginRequest,preloginRequestOffset,preloginOptions2.length);
    preloginRequestOffset=preloginRequestOffset + preloginOptions2.length;
  }
  preloginRequest[preloginRequestOffset]=TDS.B_PRELOGIN_OPTION_TERMINATOR;
  preloginRequestOffset++;
  byte[] preloginOptionData={0,0,0,0,0,0,requestedEncryptionLevel,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0};
  System.arraycopy(preloginOptionData,0,preloginRequest,preloginRequestOffset,preloginOptionData.length);
  preloginRequestOffset=preloginRequestOffset + preloginOptionData.length;
  if (fedAuthRequiredByUser) {
    preloginRequest[preloginRequestOffset]=1;
    preloginRequestOffset=preloginRequestOffset + 1;
  }
  final byte[] preloginResponse=new byte[TDS.INITIAL_PACKET_SIZE];
  String preloginErrorLogString=" Prelogin error: host " + serverName + " port " + portNumber;
  final byte[] conIdByteArray=Util.asGuidByteArray(clientConnectionId);
  int offset;
  if (fedAuthRequiredByUser) {
    offset=preloginRequest.length - 36 - 1;
  }
 else {
    offset=preloginRequest.length - 36;
  }
  System.arraycopy(conIdByteArray,0,preloginRequest,offset,conIdByteArray.length);
  offset+=conIdByteArray.length;
  if (Util.isActivityTraceOn()) {
    ActivityId activityId=ActivityCorrelator.getNext();
    final byte[] actIdByteArray=Util.asGuidByteArray(activityId.getId());
    System.arraycopy(actIdByteArray,0,preloginRequest,offset,actIdByteArray.length);
    offset+=actIdByteArray.length;
    long seqNum=activityId.getSequence();
    Util.writeInt((int)seqNum,preloginRequest,offset);
    offset+=4;
    if (connectionlogger.isLoggable(Level.FINER)) {
      connectionlogger.finer(toString() + " ActivityId " + activityId.toString());
    }
  }
  if (connectionlogger.isLoggable(Level.FINER)) {
    connectionlogger.finer(toString() + " Requesting encryption level:" + TDS.getEncryptionLevel(requestedEncryptionLevel));
  }
  if (tdsChannel.isLoggingPackets())   tdsChannel.logPacket(preloginRequest,0,preloginRequest.length,toString() + " Prelogin request");
  try {
    tdsChannel.write(preloginRequest,0,preloginRequest.length);
    tdsChannel.flush();
  }
 catch (  SQLServerException e) {
    connectionlogger.warning(toString() + preloginErrorLogString + " Error sending prelogin request: " + e.getMessage());
    throw e;
  }
  if (Util.isActivityTraceOn()) {
    ActivityCorrelator.setCurrentActivityIdSentFlag();
  }
  int responseLength=preloginResponse.length;
  int responseBytesRead=0;
  boolean processedResponseHeader=false;
  while (responseBytesRead < responseLength) {
    int bytesRead;
    try {
      bytesRead=tdsChannel.read(preloginResponse,responseBytesRead,responseLength - responseBytesRead);
    }
 catch (    SQLServerException e) {
      connectionlogger.warning(toString() + preloginErrorLogString + " Error reading prelogin response: " + e.getMessage());
      throw e;
    }
    if (-1 == bytesRead) {
      if (connectionlogger.isLoggable(Level.WARNING)) {
        connectionlogger.warning(toString() + preloginErrorLogString + " Unexpected end of prelogin response after " + responseBytesRead + " bytes read");
      }
      MessageFormat form=new MessageFormat(SQLServerException.getErrString("R_tcpipConnectionFailed"));
      Object[] msgArgs={serverName,Integer.toString(portNumber),SQLServerException.getErrString("R_notSQLServer")};
      terminate(SQLServerException.DRIVER_ERROR_IO_FAILED,form.format(msgArgs));
    }
    assert bytesRead >= 0;
    assert bytesRead <= responseLength - responseBytesRead;
    if (tdsChannel.isLoggingPackets())     tdsChannel.logPacket(preloginResponse,responseBytesRead,bytesRead,toString() + " Prelogin response");
    responseBytesRead+=bytesRead;
    if (!processedResponseHeader && responseBytesRead >= TDS.PACKET_HEADER_SIZE) {
      if (TDS.PKT_REPLY != preloginResponse[0]) {
        if (connectionlogger.isLoggable(Level.WARNING)) {
          connectionlogger.warning(toString() + preloginErrorLogString + " Unexpected response type:" + preloginResponse[0]);
        }
        MessageFormat form=new MessageFormat(SQLServerException.getErrString("R_tcpipConnectionFailed"));
        Object[] msgArgs={serverName,Integer.toString(portNumber),SQLServerException.getErrString("R_notSQLServer")};
        terminate(SQLServerException.DRIVER_ERROR_IO_FAILED,form.format(msgArgs));
      }
      if (TDS.STATUS_BIT_EOM != (TDS.STATUS_BIT_EOM & preloginResponse[1])) {
        if (connectionlogger.isLoggable(Level.WARNING)) {
          connectionlogger.warning(toString() + preloginErrorLogString + " Unexpected response status:" + preloginResponse[1]);
        }
        MessageFormat form=new MessageFormat(SQLServerException.getErrString("R_tcpipConnectionFailed"));
        Object[] msgArgs={serverName,Integer.toString(portNumber),SQLServerException.getErrString("R_notSQLServer")};
        terminate(SQLServerException.DRIVER_ERROR_IO_FAILED,form.format(msgArgs));
      }
      responseLength=Util.readUnsignedShortBigEndian(preloginResponse,2);
      assert responseLength >= 0;
      if (responseLength >= preloginResponse.length) {
        if (connectionlogger.isLoggable(Level.WARNING)) {
          connectionlogger.warning(toString() + preloginErrorLogString + " Response length:" + responseLength + " is greater than allowed length:" + preloginResponse.length);
        }
        MessageFormat form=new MessageFormat(SQLServerException.getErrString("R_tcpipConnectionFailed"));
        Object[] msgArgs={serverName,Integer.toString(portNumber),SQLServerException.getErrString("R_notSQLServer")};
        terminate(SQLServerException.DRIVER_ERROR_IO_FAILED,form.format(msgArgs));
      }
      processedResponseHeader=true;
    }
  }
  boolean receivedVersionOption=false;
  negotiatedEncryptionLevel=TDS.ENCRYPT_INVALID;
  int responseIndex=TDS.PACKET_HEADER_SIZE;
  while (true) {
    if (responseIndex >= responseLength) {
      if (connectionlogger.isLoggable(Level.WARNING)) {
        connectionlogger.warning(toString() + " Option token not found");
      }
      throwInvalidTDS();
    }
    byte optionToken=preloginResponse[responseIndex++];
    if (TDS.B_PRELOGIN_OPTION_TERMINATOR == optionToken)     break;
    if (responseIndex + 4 >= responseLength) {
      if (connectionlogger.isLoggable(Level.WARNING)) {
        connectionlogger.warning(toString() + " Offset/Length not found for option:" + optionToken);
      }
      throwInvalidTDS();
    }
    int optionOffset=Util.readUnsignedShortBigEndian(preloginResponse,responseIndex) + TDS.PACKET_HEADER_SIZE;
    responseIndex+=2;
    assert optionOffset >= 0;
    int optionLength=Util.readUnsignedShortBigEndian(preloginResponse,responseIndex);
    responseIndex+=2;
    assert optionLength >= 0;
    if (optionOffset + optionLength > responseLength) {
      if (connectionlogger.isLoggable(Level.WARNING)) {
        connectionlogger.warning(toString() + " Offset:" + optionOffset + " and length:" + optionLength + " exceed response length:" + responseLength);
      }
      throwInvalidTDS();
    }
switch (optionToken) {
case TDS.B_PRELOGIN_OPTION_VERSION:
      if (receivedVersionOption) {
        if (connectionlogger.isLoggable(Level.WARNING)) {
          connectionlogger.warning(toString() + " Version option already received");
        }
        throwInvalidTDS();
      }
    if (6 != optionLength) {
      if (connectionlogger.isLoggable(Level.WARNING)) {
        connectionlogger.warning(toString() + " Version option length:" + optionLength + " is incorrect.  Correct value is 6.");
      }
      throwInvalidTDS();
    }
  serverMajorVersion=preloginResponse[optionOffset];
if (serverMajorVersion < 9) {
  if (connectionlogger.isLoggable(Level.WARNING)) {
    connectionlogger.warning(toString() + " Server major version:" + serverMajorVersion + " is not supported by this driver.");
  }
  MessageFormat form=new MessageFormat(SQLServerException.getErrString("R_unsupportedServerVersion"));
  Object[] msgArgs={Integer.toString(preloginResponse[optionOffset])};
  terminate(SQLServerException.DRIVER_ERROR_UNSUPPORTED_CONFIG,form.format(msgArgs));
}
if (connectionlogger.isLoggable(Level.FINE)) connectionlogger.fine(toString() + " Server returned major version:" + preloginResponse[optionOffset]);
receivedVersionOption=true;
break;
case TDS.B_PRELOGIN_OPTION_ENCRYPTION:
if (TDS.ENCRYPT_INVALID != negotiatedEncryptionLevel) {
if (connectionlogger.isLoggable(Level.WARNING)) {
connectionlogger.warning(toString() + " Encryption option already received");
}
throwInvalidTDS();
}
if (1 != optionLength) {
if (connectionlogger.isLoggable(Level.WARNING)) {
connectionlogger.warning(toString() + " Encryption option length:" + optionLength + " is incorrect.  Correct value is 1.");
}
throwInvalidTDS();
}
negotiatedEncryptionLevel=preloginResponse[optionOffset];
if (TDS.ENCRYPT_OFF != negotiatedEncryptionLevel && TDS.ENCRYPT_ON != negotiatedEncryptionLevel && TDS.ENCRYPT_REQ != negotiatedEncryptionLevel && TDS.ENCRYPT_NOT_SUP != negotiatedEncryptionLevel) {
if (connectionlogger.isLoggable(Level.WARNING)) {
connectionlogger.warning(toString() + " Server returned " + TDS.getEncryptionLevel(negotiatedEncryptionLevel));
}
throwInvalidTDS();
}
if (connectionlogger.isLoggable(Level.FINER)) connectionlogger.finer(toString() + " Negotiated encryption level:" + TDS.getEncryptionLevel(negotiatedEncryptionLevel));
if (TDS.ENCRYPT_ON == requestedEncryptionLevel && TDS.ENCRYPT_ON != negotiatedEncryptionLevel && TDS.ENCRYPT_REQ != negotiatedEncryptionLevel) {
terminate(SQLServerException.DRIVER_ERROR_SSL_FAILED,SQLServerException.getErrString("R_sslRequiredNoServerSupport"));
}
if (TDS.ENCRYPT_NOT_SUP == requestedEncryptionLevel && TDS.ENCRYPT_NOT_SUP != negotiatedEncryptionLevel) {
if (TDS.ENCRYPT_REQ == negotiatedEncryptionLevel) terminate(SQLServerException.DRIVER_ERROR_SSL_FAILED,SQLServerException.getErrString("R_sslRequiredByServer"));
if (connectionlogger.isLoggable(Level.WARNING)) {
connectionlogger.warning(toString() + " Client requested encryption level: " + TDS.getEncryptionLevel(requestedEncryptionLevel) + " Server returned unexpected encryption level: " + TDS.getEncryptionLevel(negotiatedEncryptionLevel));
}
throwInvalidTDS();
}
break;
case TDS.B_PRELOGIN_OPTION_FEDAUTHREQUIRED:
if (0 != preloginResponse[optionOffset] && 1 != preloginResponse[optionOffset]) {
if (connectionlogger.isLoggable(Level.SEVERE)) {
connectionlogger.severe(toString() + " Server sent an unexpected value for FedAuthRequired PreLogin Option. Value was " + preloginResponse[optionOffset]);
}
MessageFormat form=new MessageFormat(SQLServerException.getErrString("R_FedAuthRequiredPreLoginResponseInvalidValue"));
throw new SQLServerException(form.format(new Object[]{preloginResponse[optionOffset]}),null);
}
if (((null != authenticationString) && (!authenticationString.equalsIgnoreCase(SqlAuthentication.NotSpecified.toString()))) || (null != accessTokenInByte)) {
fedAuthRequiredPreLoginResponse=(preloginResponse[optionOffset] == 1);
}
break;
default :
if (connectionlogger.isLoggable(Level.FINER)) connectionlogger.finer(toString() + " Ignoring prelogin response option:" + optionToken);
break;
}
}
if (!receivedVersionOption || TDS.ENCRYPT_INVALID == negotiatedEncryptionLevel) {
if (connectionlogger.isLoggable(Level.WARNING)) {
connectionlogger.warning(toString() + " Prelogin response is missing version and/or encryption option.");
}
throwInvalidTDS();
}
}

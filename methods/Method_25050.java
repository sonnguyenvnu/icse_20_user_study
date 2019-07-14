public String getTextPayload(){
  if (this._payloadString == null) {
    try {
      this._payloadString=binary2Text(getBinaryPayload());
    }
 catch (    CharacterCodingException e) {
      throw new RuntimeException("Undetected CharacterCodingException",e);
    }
  }
  return this._payloadString;
}

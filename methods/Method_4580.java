void stringElement(int id,String value) throws ParserException {
switch (id) {
case ID_DOC_TYPE:
    if (!DOC_TYPE_WEBM.equals(value) && !DOC_TYPE_MATROSKA.equals(value)) {
      throw new ParserException("DocType " + value + " not supported");
    }
  break;
case ID_NAME:
currentTrack.name=value;
break;
case ID_CODEC_ID:
currentTrack.codecId=value;
break;
case ID_LANGUAGE:
currentTrack.language=value;
break;
default :
break;
}
}

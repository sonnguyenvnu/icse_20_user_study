boolean isVBRIFrame(){
  int vbriOffset=header.getVBRIOffset();
  if (bytes.length < vbriOffset + 26) {
    return false;
  }
  return bytes[vbriOffset] == 'V' && bytes[vbriOffset + 1] == 'B' && bytes[vbriOffset + 2] == 'R' && bytes[vbriOffset + 3] == 'I';
}

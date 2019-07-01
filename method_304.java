public void _XXXXX_(final TagInfoBytes tagInfo,final byte... values) throws ImageWriteException {
  if (tagInfo.length > 0 && tagInfo.length != values.length) {
    throw new ImageWriteException("Tag expects " + tagInfo.length + " value(s), not "+ values.length);
  }
  final byte[] bytes=tagInfo.encodeValue(byteOrder,values);
  final TiffOutputField tiffOutputField=new TiffOutputField(tagInfo.tag,tagInfo,FieldType.BYTE,values.length,bytes);
  _XXXXX_(tiffOutputField);
}
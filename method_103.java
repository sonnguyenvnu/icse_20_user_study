public int[] _XXXXX_(final TagInfoLongs tag) throws ImageReadException {
  final TiffField field=findField(tag);
  if (field == null) {
    return null;
  }
  if (!tag.dataTypes.contains(field.getFieldType())) {
    return null;
  }
  final byte[] bytes=field.getByteArrayValue();
  return tag.getValue(field.getByteOrder(),bytes);
}
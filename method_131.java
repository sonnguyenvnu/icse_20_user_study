public RationalNumber[] _XXXXX_(final TagInfoSRationals tag,final boolean mustExist) throws ImageReadException {
  final TiffField field=findField(tag);
  if (field == null) {
    if (mustExist) {
      throw new ImageReadException("Required field \"" + tag.name + "\" is missing");
    }
 else {
      return null;
    }
  }
  if (!tag.dataTypes.contains(field.getFieldType())) {
    if (mustExist) {
      throw new ImageReadException("Required field \"" + tag.name + "\" has incorrect type "+ field.getFieldType().getName());
    }
 else {
      return null;
    }
  }
  final byte[] bytes=field.getByteArrayValue();
  return tag.getValue(field.getByteOrder(),bytes);
}
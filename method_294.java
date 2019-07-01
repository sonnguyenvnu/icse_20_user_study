public byte _XXXXX_(final TagInfoByte tag) throws ImageReadException {
  final TiffField field=findField(tag);
  if (field == null) {
    throw new ImageReadException("Required field \"" + tag.name + "\" is missing");
  }
  if (!tag.dataTypes.contains(field.getFieldType())) {
    throw new ImageReadException("Required field \"" + tag.name + "\" has incorrect type "+ field.getFieldType().getName());
  }
  if (field.getCount() != 1) {
    throw new ImageReadException("Field \"" + tag.name + "\" has wrong count "+ field.getCount());
  }
  return field.getByteArrayValue()[0];
}
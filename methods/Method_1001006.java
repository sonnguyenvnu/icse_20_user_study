/** 
 * Helper method that will append contents of given buffer into this buffer. Not particularly optimized; can be made faster if there is need.
 * @return This buffer
 */
@SuppressWarnings("resource") public TokenBuffer append(TokenBuffer other) throws IOException {
  if (!_hasNativeTypeIds) {
    _hasNativeTypeIds=other.canWriteTypeId();
  }
  if (!_hasNativeObjectIds) {
    _hasNativeObjectIds=other.canWriteObjectId();
  }
  _mayHaveNativeIds=_hasNativeTypeIds | _hasNativeObjectIds;
  JsonParser p=other.asParser();
  while (p.nextToken() != null) {
    copyCurrentStructure(p);
  }
  return this;
}

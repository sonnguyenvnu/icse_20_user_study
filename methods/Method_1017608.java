private void bind(int index,Object value,int oid,byte binary) throws SQLException {
  if (index < 1 || index > paramValues.length) {
    throw new PSQLException(GT.tr("The column index is out of range: {0}, number of columns: {1}.",index,paramValues.length),PSQLState.INVALID_PARAMETER_VALUE);
  }
  --index;
  encoded[index]=null;
  paramValues[index]=value;
  flags[index]=(byte)(direction(index) | IN | binary);
  if (oid == Oid.UNSPECIFIED && paramTypes[index] != Oid.UNSPECIFIED && value == NULL_OBJECT) {
    return;
  }
  paramTypes[index]=oid;
  pos=index + 1;
}

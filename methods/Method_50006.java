private void setOctetToHeaders(Cursor c,int columnIndex,PduHeaders headers,int mapColumn) throws InvalidHeaderValueException {
  if (!c.isNull(columnIndex)) {
    int b=c.getInt(columnIndex);
    headers.setOctet(b,mapColumn);
  }
}

/** 
 * Writes the stream prolog. 
 */
private void writeProlog() throws IOException {
  DataAtom ftypAtom=new DataAtom("ftyp");
  DataAtomOutputStream d=ftypAtom.getOutputStream();
  d.writeType("qt  ");
  d.writeBCD4(2005);
  d.writeBCD2(3);
  d.writeBCD2(0);
  d.writeType("qt  ");
  d.writeInt(0);
  d.writeInt(0);
  d.writeInt(0);
  ftypAtom.finish();
}

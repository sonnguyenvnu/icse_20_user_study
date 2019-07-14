@Override public void save(Writer writer,Properties options) throws IOException {
  writeRecons(writer,options,_oldRecons,"oldReconCount");
  writeRecons(writer,options,_newRecons,"newReconCount");
  writer.write("/ec/\n");
}

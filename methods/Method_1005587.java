public void open(InputStream stream){
  close();
  if (stream != null) {
    m_reader=new IntReader(stream,false);
  }
}

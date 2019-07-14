@Override protected void defineParameter(final StringBuilder query,final String name,final Object value,DbEntityColumnDescriptor dec){
  if (dec == null) {
    dec=templateData.lastColumnDec;
  }
  super.defineParameter(query,name,value,dec);
}

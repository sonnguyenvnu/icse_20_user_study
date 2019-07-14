static public void writeOneChange(Writer writer,Change change,Pool pool) throws IOException {
  Properties options=new Properties();
  options.setProperty("mode","save");
  options.put("pool",pool);
  writeOneChange(writer,change,options);
}

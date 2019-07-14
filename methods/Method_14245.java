static public void registerExporter(String format,Exporter exporter){
  s_formatToExporter.put(format.toLowerCase(),exporter);
}

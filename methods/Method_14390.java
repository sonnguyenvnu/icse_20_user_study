static public void registerExtension(String extension,String format){
  extensionToFormat.put(extension.startsWith(".") ? extension : ("." + extension),format);
}

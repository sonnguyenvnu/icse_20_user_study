public void scan(String base,Pattern regex,List<NutResource> list){
  for (  final String ensName : names) {
    if (!ensName.startsWith(base))     continue;
    String name=ensName;
    if (name.contains("/"))     name=name.substring(name.lastIndexOf('/') + 1);
    if (null == regex || regex.matcher(name).find()) {
      NutResource nutResource=new NutResource(){
        public InputStream getInputStream() throws IOException {
          return new URL(uriJarPrefix(uri,"!/" + ensName)).openStream();
        }
        public int hashCode(){
          return (id() + ":" + ensName).hashCode();
        }
        public String toString(){
          return uriJarPrefix(uri,"!/" + ensName);
        }
      }
;
      if (ensName.equals(base))       nutResource.setName(ensName);
 else       nutResource.setName(ensName.substring(base.length()));
      nutResource.setSource(id() + ":" + ensName);
      nutResource.setPriority(75);
      list.add(nutResource);
    }
  }
}

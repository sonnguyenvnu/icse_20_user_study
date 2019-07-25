/** 
 * ??????? CLASSPATH(?? jar) ????? <p/> <b>????</b>
 * @param src ????
 * @param regex ?????????????
 * @return ????
 */
public List<NutResource> scan(String src,String regex){
  if (src.isEmpty())   throw new RuntimeException("emtry src is NOT allow");
  if ("/".equals(src))   throw new RuntimeException("root path is NOT allow");
  List<NutResource> list=new ArrayList<NutResource>();
  Pattern pattern=regex == null ? null : Pattern.compile(regex);
  if (src.startsWith("~/"))   src=Disks.normalize(src);
  File srcFile=new File(src);
  if (srcFile.exists()) {
    if (srcFile.isDirectory()) {
      Disks.visitFile(srcFile,new ResourceFileVisitor(list,src,250),new ResourceFileFilter(pattern));
    }
 else {
      list.add(new FileResource(src,srcFile).setPriority(250));
    }
  }
  for (  ResourceLocation location : locations.values()) {
    location.scan(src,pattern,list);
  }
  if (list.isEmpty()) {
    try {
      Enumeration<URL> enu=ClassTools.getClassLoader().getResources(src);
      if (enu != null && enu.hasMoreElements()) {
        while (enu.hasMoreElements()) {
          try {
            URL url=enu.nextElement();
            ResourceLocation loc=makeResourceLocation(url);
            if (url.toString().contains("jar!"))             loc.scan(src,pattern,list);
 else             loc.scan("",pattern,list);
          }
 catch (          Throwable e) {
            if (log.isTraceEnabled())             log.trace("",e);
          }
        }
      }
    }
 catch (    Throwable e) {
      if (log.isDebugEnabled())       log.debug("Fail to run deep scan!",e);
    }
    if (list.isEmpty() && !src.endsWith("/")) {
      try {
        ClassLoader classLoader=getClass().getClassLoader();
        InputStream tmp=classLoader.getResourceAsStream(src + "/");
        if (tmp != null) {
          tmp.close();
        }
 else {
          InputStream ins=classLoader.getResourceAsStream(src);
          if (ins != null) {
            list.add(new SimpleResource(src,src,ins));
          }
        }
      }
 catch (      Exception e) {
      }
    }
  }
  List<NutResource> _list=new ArrayList<NutResource>();
  OUT:   for (  NutResource nr : list) {
    Iterator<NutResource> it=_list.iterator();
    while (it.hasNext()) {
      NutResource nr2=it.next();
      if (nr.equals(nr2)) {
        if (nr.priority > nr2.priority) {
          it.remove();
        }
 else {
          continue OUT;
        }
      }
    }
    _list.add(nr);
  }
  list=_list;
  Collections.sort(list);
  if (log.isDebugEnabled())   log.debugf("Found %s resource by src( %s ) , regex( %s )",list.size(),src,regex);
  return list;
}

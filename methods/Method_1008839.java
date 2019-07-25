private void init(org.docx4j.xmlPackage.Package flatOpcXml){
  parts=new HashMap<String,org.docx4j.xmlPackage.Part>();
  for (  org.docx4j.xmlPackage.Part p : flatOpcXml.getPart()) {
    if (log.isDebugEnabled()) {
      log.debug("Adding " + p.getName());
    }
    parts.put(p.getName(),p);
  }
}

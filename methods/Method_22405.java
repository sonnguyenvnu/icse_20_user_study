public byte[] getInstalledContribsInfo(){
  List<Contribution> contribs=getInstalledContribs();
  StringList entries=new StringList();
  for (  Contribution c : contribs) {
    String entry=c.getTypeName() + "=" + PApplet.urlEncode(String.format("name=%s\nurl=%s\nrevision=%d\nversion=%s",c.getName(),c.getUrl(),c.getVersion(),c.getBenignVersion()));
    entries.append(entry);
  }
  String joined="id=" + UpdateCheck.getUpdateID() + "&" + entries.join("&");
  return joined.getBytes();
}

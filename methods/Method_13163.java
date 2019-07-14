public void paste(API api,Object obj){
  String title="clipboard-" + (++counter) + ".log";
  URI uri=URI.create("memory://" + title);
  String content=(obj == null) ? null : obj.toString();
  api.addPanel(title,null,null,new LogPage(api,uri,content));
}

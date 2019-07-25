private String query(){
  String sql=attributes.getProperty("sql").trim();
  try {
    ScriptReader r=new ScriptReader(new StringReader(sql));
    final ArrayList<String> list=new ArrayList<>();
    while (true) {
      String s=r.readStatement();
      if (s == null) {
        break;
      }
      list.add(s);
    }
    final Connection conn=session.getConnection();
    if (SysProperties.CONSOLE_STREAM && server.getAllowChunked()) {
      String page=new String(server.getFile("result.jsp"),StandardCharsets.UTF_8);
      int idx=page.indexOf("${result}");
      list.add(0,page.substring(0,idx));
      list.add(page.substring(idx + "${result}".length()));
      session.put("chunks",new Iterator<String>(){
        @Override public boolean hasNext(){
          return i < list.size();
        }
        @Override public String next(){
          String s=list.get(i++);
          if (i == 1 || i == list.size()) {
            return s;
          }
          StringBuilder b=new StringBuilder();
          query(conn,s,i - 1,list.size() - 2,b);
          return b.toString();
        }
        @Override public void remove(){
          throw new UnsupportedOperationException();
        }
      }
);
      return "result.jsp";
    }
    String result;
    StringBuilder buff=new StringBuilder();
    for (int i=0; i < list.size(); i++) {
      String s=list.get(i);
      query(conn,s,i,list.size(),buff);
    }
    result=buff.toString();
    session.put("result",result);
  }
 catch (  Throwable e) {
    session.put("result",getStackTrace(0,e,session.getContents().isH2()));
  }
  return "result.jsp";
}

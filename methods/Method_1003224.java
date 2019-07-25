private String admin(){
  session.put("port",Integer.toString(server.getPort()));
  session.put("allowOthers",Boolean.toString(server.getAllowOthers()));
  session.put("ssl",String.valueOf(server.getSSL()));
  session.put("sessions",server.getSessions());
  return "admin.jsp";
}

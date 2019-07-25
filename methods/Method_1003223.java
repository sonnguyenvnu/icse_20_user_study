private String process(String file,NetworkConnectionInfo networkConnectionInfo){
  trace("process " + file);
  while (file.endsWith(".do")) {
switch (file) {
case "login.do":
      file=login(networkConnectionInfo);
    break;
case "index.do":
  file=index();
break;
case "logout.do":
file=logout();
break;
case "settingRemove.do":
file=settingRemove();
break;
case "settingSave.do":
file=settingSave();
break;
case "test.do":
file=test(networkConnectionInfo);
break;
case "query.do":
file=query();
break;
case "tables.do":
file=tables();
break;
case "editResult.do":
file=editResult();
break;
case "getHistory.do":
file=getHistory();
break;
case "admin.do":
file=checkAdmin(file) ? admin() : "adminLogin.do";
break;
case "adminSave.do":
file=checkAdmin(file) ? adminSave() : "adminLogin.do";
break;
case "adminStartTranslate.do":
file=checkAdmin(file) ? adminStartTranslate() : "adminLogin.do";
break;
case "adminShutdown.do":
file=checkAdmin(file) ? adminShutdown() : "adminLogin.do";
break;
case "autoCompleteList.do":
file=autoCompleteList();
break;
case "tools.do":
file=checkAdmin(file) ? tools() : "adminLogin.do";
break;
case "adminLogin.do":
file=adminLogin();
break;
default :
file="error.jsp";
break;
}
}
trace("return " + file);
return file;
}

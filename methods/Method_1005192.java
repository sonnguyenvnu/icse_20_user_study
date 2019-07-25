private String end(){
  if (oConvertUtils.isEmpty(codes)) {
    return "";
  }
  StringBuffer sb=new StringBuffer();
  List<String> optcodes=null;
  boolean flag=false;
  if (ResourceUtil.getSessionUser().getUserName().equals("admin") || !Globals.BUTTON_AUTHORITY_CHECK) {
    flag=true;
  }
 else {
    optcodes=getOperationcodes();
    if (optcodes == null || optcodes.size() <= 0) {
      flag=true;
    }
  }
  if (superQuery) {
    addAdvancedQuery(sb,codes,name);
  }
  if (codes.equals("ALL")) {
    MenuButtonsEnum[] menuArr=MenuButtonsEnum.values();
    for (int k=menuArr.length; k > 0; k--) {
      MenuButtonsEnum menu=menuArr[k - 1];
      if (flag) {
        initMenu(sb,menu);
      }
 else {
        if (hasAuth(menu.getCode(),optcodes)) {
          initMenu(sb,menu);
        }
      }
    }
  }
 else {
    if (isNotIn()) {
      MenuButtonsEnum[] menuArr=MenuButtonsEnum.values();
      for (int k=menuArr.length; k > 0; k--) {
        MenuButtonsEnum menu=menuArr[k - 1];
        if (codes.indexOf(menu.getCode()) < 0) {
          if (flag) {
            initMenu(sb,menu);
          }
 else {
            if (hasAuth(menu.getCode(),optcodes)) {
              initMenu(sb,menu);
            }
          }
        }
      }
    }
 else {
      String[] codeArr=codes.split(",");
      for (int i=codeArr.length; i > 0; i--) {
        String c=codeArr[i - 1];
        MenuButtonsEnum menu=MenuButtonsEnum.getMenuByCode(c);
        if (menu == null) {
          continue;
        }
        if (flag) {
          initMenu(sb,menu);
        }
 else {
          if (hasAuth(c,optcodes)) {
            initMenu(sb,menu);
          }
        }
      }
    }
  }
  if (this.group) {
    loadGroupJs(sb);
  }
  return sb.toString();
}

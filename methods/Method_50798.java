private static boolean isWhitelisted(List<Identifier> ids){
  StringBuffer sb=new StringBuffer();
  for (int i=0; i < ids.size(); i++) {
    sb.append(ids.get(i).getValue());
    if (i != ids.size() - 1) {
      sb.append(".");
    }
  }
switch (sb.toString().toLowerCase(Locale.ROOT)) {
case "queueable":
case "database.batchable":
case "installhandler":
    return true;
default :
  break;
}
return false;
}

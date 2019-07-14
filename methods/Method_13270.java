protected String concatIdentifiers(List<TerminalNode> identifiers){
switch (identifiers.size()) {
case 0:
    return "";
case 1:
  return identifiers.get(0).getText();
default :
sb.setLength(0);
for (TerminalNode identifier : identifiers) {
sb.append(identifier.getText()).append('/');
}
sb.setLength(sb.length() - 1);
return sb.toString();
}
}

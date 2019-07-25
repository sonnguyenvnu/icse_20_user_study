public boolean LITERAL(){
  if (this.cmd.length == 1) {
    Data.logger.warn("Syntax: LITERAL <ftp-command> [<command-argument>]   (see RFC959)");
    return true;
  }
  String s="";
  for (int i=1; i < this.cmd.length; i++) {
    s=s + " " + this.cmd[i];
  }
  try {
    literal(s.substring(1));
  }
 catch (  final IOException e) {
    Data.logger.warn("Error: Syntax of FTP-command wrong. See RFC959 for details.");
  }
  return true;
}

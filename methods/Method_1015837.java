public void print(String s){
  for (  ChatColor color : colors) {
    s=s.replaceAll("(?i)" + color.toString(),replacements.get(color));
  }
  try {
    console.print(Ansi.ansi().eraseLine(Erase.ALL).toString() + ConsoleReader.RESET_LINE + s + Ansi.ansi().reset().toString());
    console.drawLine();
    console.flush();
  }
 catch (  IOException ex) {
  }
}

private void run(){
  Scanner in=new Scanner(System.in);
  help();
  while (in.hasNextLine()) {
    try {
      String cmdLine=in.nextLine().trim();
      String[] cmdArray=cmdLine.split("\\s+");
      String cmd=cmdArray[0];
      if ("".equals(cmd)) {
        continue;
      }
      String cmdLowerCase=cmd.toLowerCase();
switch (cmdLowerCase) {
case "help":
{
          help();
          break;
        }
case "genkeystore":
{
        genKeystore();
        break;
      }
case "importprivatekey":
{
      importPrivatekey();
      break;
    }
case "exit":
case "quit":
{
    System.out.println("Exit !!!");
    in.close();
    return;
  }
default :
{
  System.out.println("Invalid cmd: " + cmd);
  help();
}
}
}
 catch (Exception e) {
logger.error(e.getMessage());
}
}
}

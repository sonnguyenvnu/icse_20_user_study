/** 
 * Attempt to execute a command. This version takes a separate command name (for the root command) and then a list of following arguments.
 * @param cmd command to run
 * @param args arguments
 * @param player command source
 * @param methodArgs method arguments
 * @throws CommandException thrown when the command throws an error
 */
public void execute(String cmd,String[] args,T player,Object... methodArgs) throws CommandException {
  String[] newArgs=new String[args.length + 1];
  System.arraycopy(args,0,newArgs,1,args.length);
  newArgs[0]=cmd;
  Object[] newMethodArgs=new Object[methodArgs.length + 1];
  System.arraycopy(methodArgs,0,newMethodArgs,1,methodArgs.length);
  executeMethod(null,newArgs,player,newMethodArgs,0);
}

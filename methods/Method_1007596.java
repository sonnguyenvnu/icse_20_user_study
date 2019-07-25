/** 
 * Attempt to execute a command.
 * @param args the arguments
 * @param player the player
 * @param methodArgs the arguments for the method
 * @throws CommandException thrown on command error
 */
public void execute(String[] args,T player,Object... methodArgs) throws CommandException {
  Object[] newMethodArgs=new Object[methodArgs.length + 1];
  System.arraycopy(methodArgs,0,newMethodArgs,1,methodArgs.length);
  executeMethod(null,args,player,newMethodArgs,0);
}

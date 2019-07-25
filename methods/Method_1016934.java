/** 
 * Give this CommandOption the opportunity to process the index'th argument in args. Return the next unprocessed index. 
 */
public int process(java.lang.String[] args,int index){
  if (args.length == 0)   return index;
  if (index >= args.length || args[index] == null || args[index].length() < 2 || args[index].charAt(0) != '-' || args[index].charAt(1) != '-')   return index;
  java.lang.String optFullName=args[index].substring(2);
  int dotIndex=optFullName.lastIndexOf('.');
  java.lang.String optName=optFullName;
  if (dotIndex != -1) {
    java.lang.String optPackageName=optFullName.substring(0,dotIndex);
    if (owner.getPackage() != null && !owner.getPackage().toString().endsWith(optPackageName))     return index;
    optName=optFullName.substring(dotIndex + 1);
  }
  if (!name.equals(optName))   return index;
  this.invoked=true;
  index++;
  if (args.length > index && (args[index].length() < 2 || (args[index].charAt(0) != '-' && args[index].charAt(1) != '-'))) {
    index=parseArg(args,index);
  }
 else {
    if (argRequired) {
      throw new IllegalArgumentException("Missing argument for option " + optName);
    }
 else {
      parseArg(args,-index);
    }
  }
  return index;
}

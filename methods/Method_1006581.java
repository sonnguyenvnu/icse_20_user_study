public static int complete(CommandSpec spec,String[] args,int argIndex,int positionInArg,int cursor,List<CharSequence> candidates){
  if (spec == null) {
    throw new NullPointerException("spec is null");
  }
  if (args == null) {
    throw new NullPointerException("args is null");
  }
  if (candidates == null) {
    throw new NullPointerException("candidates list is null");
  }
  if (argIndex == args.length) {
    String[] copy=new String[args.length + 1];
    System.arraycopy(args,0,copy,0,args.length);
    args=copy;
    args[argIndex]="";
  }
  if (argIndex < 0 || argIndex >= args.length) {
    throw new IllegalArgumentException("Invalid argIndex " + argIndex + ": args array only has " + args.length + " elements.");
  }
  if (positionInArg < 0 || positionInArg > args[argIndex].length()) {
    throw new IllegalArgumentException("Invalid positionInArg " + positionInArg + ": args[" + argIndex + "] (" + args[argIndex] + ") only has " + args[argIndex].length() + " characters.");
  }
  String currentArg=args[argIndex];
  boolean reset=spec.parser().collectErrors();
  try {
    String committedPrefix=currentArg.substring(0,positionInArg);
    spec.parser().collectErrors(true);
    CommandLine parser=new CommandLine(spec);
    ParseResult parseResult=parser.parseArgs(args);
    if (argIndex >= parseResult.tentativeMatch.size()) {
      Object startPoint=findCompletionStartPoint(parseResult);
      addCandidatesForArgsFollowing(startPoint,candidates);
    }
 else {
      Object obj=parseResult.tentativeMatch.get(argIndex);
      if (obj instanceof CommandSpec) {
        addCandidatesForArgsFollowing(((CommandSpec)obj).parent(),candidates);
      }
 else       if (obj instanceof OptionSpec) {
        int sep=currentArg.indexOf(spec.parser().separator());
        if (sep < 0 || positionInArg < sep) {
          addCandidatesForArgsFollowing(findCommandFor((OptionSpec)obj,spec),candidates);
        }
 else {
          addCandidatesForArgsFollowing((OptionSpec)obj,candidates);
          int sepLength=spec.parser().separator().length();
          if (positionInArg < sep + sepLength) {
            int posInSeparator=positionInArg - sep;
            String prefix=spec.parser().separator().substring(posInSeparator);
            for (int i=0; i < candidates.size(); i++) {
              candidates.set(i,prefix + candidates.get(i));
            }
            committedPrefix=currentArg.substring(sep,positionInArg);
          }
 else {
            committedPrefix=currentArg.substring(sep + sepLength,positionInArg);
          }
        }
      }
 else       if (obj instanceof PositionalParamSpec) {
        addCandidatesForArgsFollowing(findCommandFor((PositionalParamSpec)obj,spec),candidates);
      }
 else {
        int i=argIndex - 1;
        while (i > 0 && !isPicocliModelObject(parseResult.tentativeMatch.get(i))) {
          i--;
        }
        if (i < 0) {
          return -1;
        }
        addCandidatesForArgsFollowing(parseResult.tentativeMatch.get(i),candidates);
      }
    }
    filterAndTrimMatchingPrefix(committedPrefix,candidates);
    return candidates.isEmpty() ? -1 : cursor;
  }
  finally {
    spec.parser().collectErrors(reset);
  }
}

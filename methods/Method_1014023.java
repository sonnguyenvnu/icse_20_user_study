@Override public void execute(String[] args,Console console){
  if (args.length > 0) {
    String subCommand=args[0];
switch (subCommand) {
case SUBCMD_PLAY:
      if (args.length > 1) {
        play((String[])ArrayUtils.subarray(args,1,args.length),console);
      }
 else {
        console.println("Specify file to play, and optionally the sink(s) to use (e.g. 'play javasound hello.mp3')");
      }
    return;
case SUBCMD_STREAM:
  if (args.length > 1) {
    stream((String[])ArrayUtils.subarray(args,1,args.length),console);
  }
 else {
    console.println("Specify url to stream from, and optionally the sink(s) to use");
  }
return;
case SUBCMD_SOURCES:
listSources(console);
return;
case SUBCMD_SINKS:
listSinks(console);
return;
default :
break;
}
}
 else {
printUsage(console);
}
}

public static BiboxCommands transferCommand(BiboxTransferCommandBody body){
  return BiboxCommands.of(new BiboxCommand<BiboxTransferCommandBody>("transfer/transferOut",body));
}

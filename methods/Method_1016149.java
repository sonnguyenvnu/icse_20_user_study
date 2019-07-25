public static void install(DictionariesTree tree){
  tree.setTransferHandler(new DictionariesTransferHandler(tree));
}

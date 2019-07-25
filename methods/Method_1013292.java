private static void put(UniqueString op,int opcode){
  int loc=op.getTok();
  if (loc >= OpCodeTable.length) {
    int len1=loc + 20;
    int[] OpCodeTable1=new int[len1];
    for (int i=0; i < OpCodeTable.length; i++) {
      OpCodeTable1[i]=OpCodeTable[i];
    }
    OpCodeTable=OpCodeTable1;
  }
  OpCodeTable[loc]=opcode;
}

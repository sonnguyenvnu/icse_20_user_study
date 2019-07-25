public static BasicBlock find(BasicBlock[] blocks,int pos) throws BadBytecode {
  for (  BasicBlock b : blocks)   if (b.position <= pos && pos < b.position + b.length)   return b;
  throw new BadBytecode("no basic block at " + pos);
}

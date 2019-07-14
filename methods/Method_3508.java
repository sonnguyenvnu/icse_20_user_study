protected static Nature toDefaultNature(String compiledChar){
  if (compiledChar.equals("M"))   return Nature.m;
  if (compiledChar.equals("W"))   return Nature.nx;
  return null;
}

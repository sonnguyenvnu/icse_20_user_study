public List<Symbol> symbolAll() throws DragonexException, IOException {
  DragonResult<List<Symbol>> symbolAll=exchange.dragonexPublic().symbolAll();
  return symbolAll.getResult();
}

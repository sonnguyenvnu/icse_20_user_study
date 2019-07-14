/** 
 * ?????????
 * @param realWord ????
 * @param attribute ????
 * @return
 */
private String compileRealWord(String realWord,CoreDictionary.Attribute attribute){
  if (attribute.nature.length == 1) {
    Nature nature=attribute.nature[0];
    if (nature.startsWith("nr")) {
      wordID=CoreDictionary.NR_WORD_ID;
      return Predefine.TAG_PEOPLE;
    }
 else     if (nature.startsWith("ns")) {
      wordID=CoreDictionary.NS_WORD_ID;
      return Predefine.TAG_PLACE;
    }
 else     if (nature == Nature.nx) {
      wordID=CoreDictionary.NX_WORD_ID;
      if (wordID == -1)       wordID=CoreDictionary.X_WORD_ID;
      return Predefine.TAG_PROPER;
    }
 else     if (nature.startsWith("nt") || nature == Nature.nit) {
      wordID=CoreDictionary.NT_WORD_ID;
      return Predefine.TAG_GROUP;
    }
 else     if (nature.startsWith('m')) {
      wordID=CoreDictionary.M_WORD_ID;
      this.attribute=CoreDictionary.get(CoreDictionary.M_WORD_ID);
      return Predefine.TAG_NUMBER;
    }
 else     if (nature.startsWith('x')) {
      wordID=CoreDictionary.X_WORD_ID;
      this.attribute=CoreDictionary.get(CoreDictionary.X_WORD_ID);
      return Predefine.TAG_CLUSTER;
    }
 else     if (nature == Nature.t) {
      wordID=CoreDictionary.T_WORD_ID;
      this.attribute=CoreDictionary.get(CoreDictionary.T_WORD_ID);
      return Predefine.TAG_TIME;
    }
  }
  return realWord;
}

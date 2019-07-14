/** 
 * @see {@link RxTickerView#setCharacterList(char[])}.
 */
void setCharacterList(char[] characterList){
  this.characterList=characterList;
  this.characterIndicesMap=new HashMap<>(characterList.length);
  for (int i=0; i < characterList.length; i++) {
    characterIndicesMap.put(characterList[i],i);
  }
}

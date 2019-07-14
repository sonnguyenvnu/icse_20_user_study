/** 
 * ??????????????????
 * @param item
 */
public void addNotCombine(Item item){
  Item innerItem=trie.get(item.key);
  if (innerItem == null) {
    innerItem=item;
    trie.put(innerItem.key,innerItem);
  }
}

/** 
 * Removes object from current bag, indicating it is not anymore in the path.
 */
public void popValue(){
  bagSize--;
  if (bagSize == 0) {
    lastValueContext=null;
  }
 else {
    lastValueContext=bag.get(bagSize - 1);
  }
}

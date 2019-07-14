/** 
 * ???
 */
void init(){
  _table.resize(INITIAL_TABLE_SIZE,0);
  appendNode();
  appendUnit();
  _numStates=1;
  _nodes.get(0).label=(byte)0xFF;
  _nodeStack.add(0);
}

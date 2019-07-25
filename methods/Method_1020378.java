/** 
 * Calculates the dominance frontier information for the method.
 * @return {@code non-null;} an array of DomInfo structures
 */
public DomInfo[] run(){
  int szNodes=nodes.size();
  if (DEBUG) {
    for (int i=0; i < szNodes; i++) {
      SsaBasicBlock node=nodes.get(i);
      System.out.println("pred[" + i + "]: " + node.getPredecessors());
    }
  }
  if (DEBUG) {
    for (int i=0; i < szNodes; i++) {
      DomInfo info=domInfos[i];
      System.out.println("idom[" + i + "]: " + info.idom);
    }
  }
  buildDomTree();
  if (DEBUG) {
    debugPrintDomChildren();
  }
  for (int i=0; i < szNodes; i++) {
    domInfos[i].dominanceFrontiers=SetFactory.makeDomFrontSet(szNodes);
  }
  calcDomFronts();
  if (DEBUG) {
    for (int i=0; i < szNodes; i++) {
      System.out.println("df[" + i + "]: " + domInfos[i].dominanceFrontiers);
    }
  }
  return domInfos;
}

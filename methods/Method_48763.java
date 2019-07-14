/** 
 * Whether all individual orders are the same
 * @return
 */
public boolean hasCommonOrder(){
  Order lastOrder=null;
  for (  OrderEntry oe : list) {
    if (lastOrder == null)     lastOrder=oe.order;
 else     if (lastOrder != oe.order)     return false;
  }
  return true;
}

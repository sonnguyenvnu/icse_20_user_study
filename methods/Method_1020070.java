/** 
 * Focus on the tail of an  {@link HList}.
 * @param < Head > the head element type
 * @param < Tail > the tail HList type
 * @return a lens that focuses on the tail of an HList
 */
public static <Head,Tail extends HList>Lens.Simple<HCons<Head,? extends Tail>,Tail> tail(){
  return simpleLens(HCons::tail,(hCons,newTail) -> cons(hCons.head(),newTail));
}

/** 
 * ????????,???????
 * @param dataList      ???????
 * @param childConsumer ???????
 * @param < N >           ?????
 * @param < PK >          ????
 * @return ??????
 */
static <N extends TreeSupportEntity<PK>,PK>List<N> list2tree(Collection<N> dataList,BiConsumer<N,List<N>> childConsumer){
  return list2tree(dataList,childConsumer,(Function<TreeHelper<N,PK>,Predicate<N>>)predicate -> node -> node == null || predicate.getNode(node.getParentId()) == null);
}

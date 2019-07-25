@SuppressWarnings("deprecation") public static void persists(DStream<Tuple2<Integer,Iterable<Long>>> partitonOffset,Properties props){
  ClassTag<Tuple2<Integer,Iterable<Long>>> tuple2ClassTag=ScalaUtil.<Integer,Iterable<Long>>getTuple2ClassTag();
  JavaDStream<Tuple2<Integer,Iterable<Long>>> jpartitonOffset=new JavaDStream<Tuple2<Integer,Iterable<Long>>>(partitonOffset,tuple2ClassTag);
  jpartitonOffset.foreachRDD(new VoidFunction<JavaRDD<Tuple2<Integer,Iterable<Long>>>>(){
    @Override public void call(    JavaRDD<Tuple2<Integer,Iterable<Long>>> po) throws Exception {
      List<Tuple2<Integer,Iterable<Long>>> poList=po.collect();
      doPersists(poList,props);
    }
  }
);
}

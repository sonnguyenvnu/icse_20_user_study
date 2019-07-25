List<ShrinkNode> shrinks(){
  return IntStream.range(0,params.size()).mapToObj(i -> params.get(i).shrink(args[i]).stream().filter(o -> !o.equals(args[i])).map(o -> shrinkNodeFor(o,i))).flatMap(identity()).collect(toList());
}

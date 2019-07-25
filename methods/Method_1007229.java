static F<Integer,Writer<String,Integer>> half(){
  return x -> Writer.unit(x / 2,stringMonoid).tell("I just2 halved " + x + "!");
}

private static Object[] createComposite(List<Object> elementPairs){
switch (elementPairs.size()) {
case 0:
    return new Object[]{Literal.EMPTY,Literal.EMPTY};
case 1:
  return new Object[]{elementPairs.get(0),elementPairs.get(1)};
default :
Composite comp=new Composite(elementPairs);
return new Object[]{comp,comp};
}
}

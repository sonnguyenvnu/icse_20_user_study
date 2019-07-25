public FrontierStack restore(){
  final List<Stack> result=new ArrayList<Stack>(all);
  final Stack openedBar=result.get(result.size() - 2);
  final Stack lastStack=result.get(result.size() - 1);
  result.set(result.size() - 2,openedBar.addEnvelop(lastStack.current));
  result.remove(result.size() - 1);
  final Stack s=new Stack(openedBar.current.copy());
  result.add(s);
  return new FrontierStackImpl(result);
}

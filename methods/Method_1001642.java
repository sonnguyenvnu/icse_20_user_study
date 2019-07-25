public double tic(Board board,Random rnd){
  final double costBefore=costComputer.getCost(board);
  final Move move=null;
  board.applyMove(move);
  final double costAfter=costComputer.getCost(board);
  final double delta=costAfter - costBefore;
  if (delta <= 0) {
    return costAfter;
  }
  assert delta > 0;
  assert costAfter > costBefore;
  if (temp > 0) {
    final double probability=Math.exp(-delta / temp);
    final double dice=rnd.nextDouble();
    if (dice < probability) {
      return costAfter;
    }
  }
  board.applyMove(move.getBackMove());
  assert costBefore == costComputer.getCost(board);
  return costBefore;
}

private void close(){
  if (lastCurrent.equals(end) == false) {
    lines.add(new Line2D.Double(lastCurrent,end));
  }
}

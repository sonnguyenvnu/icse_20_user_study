public void clean(int remaining){
  while (this.lines.size() > remaining) {
    this.lines.poll();
  }
}

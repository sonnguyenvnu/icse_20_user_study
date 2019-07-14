public void addNext(SimpleTypedNameDeclaration next){
  if (next == null) {
    return;
  }
  if (this.next == null) {
    this.next=next;
  }
 else {
    this.next.addNext(next);
  }
}

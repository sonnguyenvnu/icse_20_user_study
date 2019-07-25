CNode copy(){
  return new CNode(this.token,this.children,this.subscriptions);
}

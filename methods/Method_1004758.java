String mapping(){
  if (typed) {
    return index + "/_mapping/" + type;
  }
 else {
    return index + "/_mapping";
  }
}

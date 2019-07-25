public int next(){
  return (curPageNO + 1 > pageCount) ? pageCount : curPageNO + 1;
}

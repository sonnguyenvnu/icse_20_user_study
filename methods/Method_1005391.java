public MenuItem next(){
  MenuItem item=items.get(position);
  position=position + 1;
  return item;
}

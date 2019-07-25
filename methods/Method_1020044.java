public void rewind(){
  if (cache.isEmpty())   throw new NoSuchElementException();
  rewound=true;
}

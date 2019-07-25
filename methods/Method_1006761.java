@RequestMapping("/create") public ViewEntity create(){
  Cat cat=new Cat();
  cat.setId(245);
  cat.setDogId(2);
  Cat cat2=new Cat();
  cat2.setId(246);
  cat2.setDogId(2);
  List<Cat> catList=new ArrayList<>();
  for (int i=0; i < 500; i++) {
    Cat cat3=new Cat();
    cat3.setId(5448 + i);
    cat3.setDogId(2);
    catList.add(cat3);
  }
  this.catRepository.createBatch(catList);
  return ViewEntity.ok();
}

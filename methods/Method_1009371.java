public static void main(String... args){
  ModelMapper modelMapper=new ModelMapper();
  modelMapper.addConverter(new Converter<Car,AnotherCar>(){
    public AnotherCar convert(    MappingContext<Car,AnotherCar> context){
      Person person=(Person)context.getParent().getParent().getSource();
      context.getDestination().setPersonName(person.getName());
      context.getDestination().setType(context.getSource().getType());
      return context.getDestination();
    }
  }
);
  modelMapper.addMappings(new PropertyMap<Person,AnotherPerson>(){
    @Override protected void configure(){
      map(source.getCars()).setAnotherCars(null);
    }
  }
);
  Person person=new Person();
  person.name="joe";
  Car car1=new Car();
  car1.type="Honda";
  Car car2=new Car();
  car2.type="Toyota";
  person.cars=Arrays.asList(car1,car2);
  AnotherPerson anotherPerson=modelMapper.map(person,AnotherPerson.class);
  assertEquals(anotherPerson.getCars().get(0).personName,"joe");
  assertEquals(anotherPerson.getCars().get(0).type,car1.type);
  assertEquals(anotherPerson.getCars().get(1).personName,"joe");
  assertEquals(anotherPerson.getCars().get(1).type,car2.type);
}

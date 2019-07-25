public static View create(Address coord,long id,Address... members){
  return new View(new ViewId(coord,id),members);
}

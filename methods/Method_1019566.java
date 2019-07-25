@Bean Catalogue catalogue(CatalogueDatabase catalogueDatabase,DomainEvents domainEvents){
  return new Catalogue(catalogueDatabase,domainEvents);
}

@JsonIgnore public List<String> getSuggestedPrices(){
  final List<String> suggestedPrices=new ArrayList<>();
  suggestedPrices.add(button.price1);
  suggestedPrices.add(button.price2);
  suggestedPrices.add(button.price3);
  suggestedPrices.add(button.price4);
  suggestedPrices.add(button.price5);
  return suggestedPrices;
}

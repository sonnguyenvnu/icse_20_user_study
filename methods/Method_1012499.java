void execute(){
  pins[0].value=pins[2].value ^ pins[3].value;
  pins[1].value=pins[2].value && pins[3].value;
}

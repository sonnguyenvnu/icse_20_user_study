/** 
 * Configures the SPI interface
 * @param maxSpeed maximum transmission rate in Hz, 500000 (500 kHz) is a resonable default
 * @param dataOrder whether data is send with the first- or least-significant bit first (SPI.MSBFIRST or SPI.LSBFIRST, the former is more common)
 * @param mode <a href="https://en.wikipedia.org/wiki/Serial_Peripheral_Interface_Bus#Clock_polarity_and_phase">SPI.MODE0 to SPI.MODE3</a>
 * @webref
 */
public void settings(int maxSpeed,int dataOrder,int mode){
  this.maxSpeed=maxSpeed;
  this.dataOrder=dataOrder;
  this.mode=mode;
}

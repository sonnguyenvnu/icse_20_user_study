/** 
 * Returns a  {@link ScannerSupplier} that just returns the {@link Scanner} that was passed in.Used mostly for testing. Does not implement any method other than  {@link ScannerSupplier#get()}.
 */
public static ScannerSupplier fromScanner(Scanner scanner){
  return new InstanceReturningScannerSupplierImpl(scanner);
}

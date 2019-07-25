@NotNull private static CharSequence decompiled(@SuppressWarnings("OptionalUsedAsFieldOrParameterType") @NotNull Optional<Beam> beamOptional){
  StringBuilder decompiled=new StringBuilder("# Decompilation Error: ");
  if (beamOptional.isPresent()) {
    Beam beam=beamOptional.get();
    Atoms atoms=beam.atoms();
    if (atoms != null) {
      String moduleName=atoms.moduleName();
      if (moduleName != null) {
        String defmoduleArgument=defmoduleArgument(moduleName);
        decompiled=new StringBuilder("# Source code recreated from a .beam file by IntelliJ Elixir\n").append("defmodule ").append(defmoduleArgument).append(" do\n");
        appendCallDefinitions(decompiled,beam,atoms);
        decompiled.append("end\n");
      }
 else {
        decompiled.append("No module name found in ").append(ATOM).append(" chunk in BEAM");
      }
    }
 else {
      decompiled.append("No ").append(ATOM).append(" chunk found in BEAM");
    }
  }
 else {
    decompiled.append("BEAM format could not be read");
  }
  return decompiled;
}

/*
 * Copyright 2003-2018 JetBrains s.r.o.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package jetbrains.mps.project;

import jetbrains.mps.classloading.CustomClassLoadingFacet;
import jetbrains.mps.java.stub.PackageScopeControl;
import jetbrains.mps.module.ReloadableModuleBase;
import jetbrains.mps.project.facets.TestsFacet;
import jetbrains.mps.project.io.DescriptorIO;
import jetbrains.mps.project.io.DescriptorIOFacade;
import jetbrains.mps.project.structure.model.ModelRootDescriptor;
import jetbrains.mps.project.structure.modules.ModuleDescriptor;
import jetbrains.mps.project.structure.modules.SolutionDescriptor;
import jetbrains.mps.project.structure.modules.SolutionKind;
import jetbrains.mps.reloading.CommonPaths;
import jetbrains.mps.smodel.BootstrapLanguages;
import jetbrains.mps.util.ClassType;
import jetbrains.mps.util.annotation.Hack;
import jetbrains.mps.vfs.IFile;
import org.apache.log4j.Logger;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.jetbrains.mps.openapi.module.SModuleReference;
import org.jetbrains.mps.openapi.persistence.Memento;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * Igor Alshannikov
 * Aug 26, 2005
 */
public class Solution extends ReloadableModuleBase {
  private SolutionDescriptor mySolutionDescriptor;
  public static final String SOLUTION_MODELS = "models";
  // idea plugin wants to turn it off sometimes, when it knows better what jdk is and what platform is
  private boolean myUpdateBootstrapSolutions = true;

  private static Map<SModuleReference, ClassType> bootstrapCP = initBootstrapSolutions();

  private static Map<SModuleReference, ClassType> initBootstrapSolutions() {
    Map<SModuleReference, ClassType> result = new HashMap<>();
    result.put(BootstrapLanguages.jdkRef(), ClassType.JDK);
    result.put(BootstrapLanguages.jdkToolsRef(), ClassType.JDK_TOOLS);
    result.put(new jetbrains.mps.project.structure.modules.ModuleReference("Annotations",
        ModuleId.fromString("3f233e7f-b8a6-46d2-a57f-795d56775243")), ClassType.ANNOTATIONS);
    result.put(new jetbrains.mps.project.structure.modules.ModuleReference("MPS.OpenAPI",
        ModuleId.fromString("8865b7a8-5271-43d3-884c-6fd1d9cfdd34")), ClassType.OPENAPI);
    result.put(new jetbrains.mps.project.structure.modules.ModuleReference("MPS.Core",
        ModuleId.fromString("6ed54515-acc8-4d1e-a16c-9fd6cfe951ea")), ClassType.CORE);
    result.put(new jetbrains.mps.project.structure.modules.ModuleReference("MPS.Editor",
        ModuleId.fromString("1ed103c3-3aa6-49b7-9c21-6765ee11f224")), ClassType.EDITOR);
    result.put(new jetbrains.mps.project.structure.modules.ModuleReference("MPS.Platform",
        ModuleId.fromString("742f6602-5a2f-4313-aa6e-ae1cd4ffdc61")), ClassType.PLATFORM);
    result.put(new jetbrains.mps.project.structure.modules.ModuleReference("MPS.IDEA",
        ModuleId.fromString("498d89d2-c2e9-11e2-ad49-6cf049e62fe5")), ClassType.IDEA);
    result.put(new jetbrains.mps.project.structure.modules.ModuleReference("MPS.Workbench",
        ModuleId.fromString("86441d7a-e194-42da-81a5-2161ec62a379")), ClassType.WORKBENCH);
    result.put(new jetbrains.mps.project.structure.modules.ModuleReference("Testbench",
        ModuleId.fromString("920eaa0e-ecca-46bc-bee7-4e5c59213dd6")), ClassType.TEST);
    return result;
  }

  /* TODO make package local, move to appropriate package */
  public Solution(SolutionDescriptor descriptor, @Nullable IFile file) {
    super(file);
    mySolutionDescriptor = descriptor;
    setModuleReference(descriptor.getModuleReference());
  }

  private static void populateModelRoot(ClassType classType, ModelRootDescriptor javaStubsModelRoot) {
    PackageScopeControl psc = null;
    switch (classType) {
      case JDK:
        PackageScopeControl jdkPackages = new PackageScopeControl();
        jdkPackages.setSkipPrivate(true);
        jdkPackages.includeWithPrefix("java.");
        jdkPackages.includeWithPrefix("javax.");
        jdkPackages.includeWithPrefix("org.");
        // java fx (from ext/jfxrt.jar)
        jdkPackages.includeWithPrefix("javafx.");
        jdkPackages.includeWithPrefix("netscape.javascript.");
        // sun.awt used in mbeddr
        jdkPackages.includeWithPrefix("sun.awt.");
        psc = jdkPackages;
        break;
      case JDK_TOOLS:
        psc = new PackageScopeControl();
        psc.isSkipPrivate();
        psc.includeWithPrefix("com.sun.codemodel.");
        psc.includeWithPrefix("com.sun.source.");
        psc.includeWithPrefix("com.sun.tools.");
        psc.includeWithPrefix("com.sun.jarsigner.");
        psc.includeWithPrefix("com.sun.javadoc.");
        psc.includeWithPrefix("com.sun.jdi.");
        psc.includeWithPrefix("org.relaxng.");
        psc.includeWithPrefix("sun.jvmstat.");
        psc.includeWithPrefix("sun.rmi.rmic.");
        psc.includeWithPrefix("sun.tools.");
        psc.includeWithPrefix("sun.applet.");
        break;
      case PLATFORM:
      case IDEA:
        PackageScopeControl platformPackages = new PackageScopeControl();
        // mbeddr uses reflection (though custom dsl) to access MPS internals
        // hence we need to expose private methods unless this reflection language and its uses are removed
        //      platformPackages.setSkipPrivate(true);
        psc = platformPackages;
        break;
    }

    if (psc != null) {
      final Memento m = javaStubsModelRoot.getMemento().createChild("PackageScope");
      psc.save(m);
    }
  }

  @NotNull
  @Override
  public SolutionDescriptor getModuleDescriptor() {
    return mySolutionDescriptor;
  }

  @Override
  protected void doSetModuleDescriptor(ModuleDescriptor moduleDescriptor) {
    mySolutionDescriptor = (SolutionDescriptor) moduleDescriptor;
    SModuleReference mp;
    if (mySolutionDescriptor.getNamespace() != null) {
      mp = new jetbrains.mps.project.structure.modules.ModuleReference(mySolutionDescriptor.getNamespace(), mySolutionDescriptor.getId());
    } else {
      IFile descriptorFile = getDescriptorFile();
      assert descriptorFile != null;
      mp = new jetbrains.mps.project.structure.modules.ModuleReference(descriptorFile.getPath(), mySolutionDescriptor.getId());
    }

    setModuleReference(mp);
  }

  public void setUpdateBootstrapSolutions(boolean b) {
    myUpdateBootstrapSolutions = b;
  }

  @Override
  public void save() {
    super.save();
    //do not save stub solutions (otherwise build model generation fails)
    SModuleReference ref = this.getModuleReference();
    if (isBootstrapSolution(ref)) return;
    // in StubSolutions myDescriptorFile is null, so preventing NPE here (MPS-16793)
    if (getDescriptorFile() == null || isReadOnly()) return;

    if (mySolutionDescriptor.getLoadException() != null){
      return;
    }

    try {
      DescriptorIO<SolutionDescriptor> io = DescriptorIOFacade.getInstance().standardProvider().solutionDescriptorIO();
      io.writeToFile(getModuleDescriptor(), getDescriptorFile());
    } catch (Exception ex) {
      Logger.getLogger(getClass()).error("Save failed", ex);
    }
  }

  public static boolean isBootstrapSolution(SModuleReference ref) {
    return bootstrapCP.keySet().contains(ref);
  }

  @Override
  public void updateModelsSet() {
    if (myUpdateBootstrapSolutions) {
      updateBootstrapSolutionLibraries();
    }
    super.updateModelsSet();
  }

  @Hack
  private void updateBootstrapSolutionLibraries() {
    ModuleDescriptor descriptor = getModuleDescriptor();

    ClassType classType = bootstrapCP.get(descriptor.getModuleReference());
    if (classType == null) return;

    // do it only for first time
    if (descriptor.getModelRootDescriptors().isEmpty()) {
      for (String path : CommonPaths.getMPSPaths(classType)) {
        final Collection<ModelRootDescriptor> modelRootDescriptors = descriptor.getModelRootDescriptors();
        IFile pathFile = getFileSystem().getFile(path);
        final ModelRootDescriptor javaStubsModelRoot = ModelRootDescriptor.addSourceRoot(pathFile, modelRootDescriptors);
        if (javaStubsModelRoot != null) {
          modelRootDescriptors.add(javaStubsModelRoot);
          populateModelRoot(classType, javaStubsModelRoot);
        }
        descriptor.getJavaLibs().add(path);
      }
    }
  }

  public String toString() {
    return getModuleName() + " [solution]";
  }

  public SolutionKind getKind() {
    return getModuleDescriptor().getKind();
  }

  @Override
  protected void collectMandatoryFacetTypes(Set<String> types) {
    super.collectMandatoryFacetTypes(types);
    types.add(TestsFacet.FACET_TYPE);
  }

  @Override
  public boolean canLoadClasses() {
    // TODO mps facet from this [like IDEA plugin facet]
    return getKind() != SolutionKind.NONE || getFacet(CustomClassLoadingFacet.class) != null;
  }
}

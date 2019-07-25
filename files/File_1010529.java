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
package jetbrains.mps.project.structure;

import jetbrains.mps.extapi.module.SModuleBase;
import jetbrains.mps.project.Solution;
import jetbrains.mps.smodel.BootstrapLanguages;
import jetbrains.mps.smodel.SModelId.IntegerSModelId;
import jetbrains.mps.smodel.SModelStereotype;
import jetbrains.mps.smodel.SnapshotModelData;
import jetbrains.mps.smodel.TrivialModelDescriptor;
import org.jetbrains.mps.openapi.model.SModelId;
import org.jetbrains.mps.openapi.model.SModelName;
import org.jetbrains.mps.openapi.model.SModelReference;
import org.jetbrains.mps.openapi.module.SModule;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 * We need @descriptor model for any module we are going to build and deploy, no reason to keep distinct provider for different module kind
 * (language, generator, solution and devkit) unless there's true need.
 *
 * XXX Why there's no easy way to access descriptor model for a module, other than iterate over all models to find one with
 * "@descriptor" stereotype?! I'd like to have e.g. getDescriptorModel(Solution) that would use model id instead of "@descriptor"!
 *
 * @author Artem Tikhomirov
 * @since 2018.3
 */
public class GenericDescriptorModelProvider extends DescriptorModelProvider {
  private final SModelId myDescriptorModelId = new IntegerSModelId(0x0f040404);

  private final Map<SModelReference, DescriptorModel> myModels = Collections.synchronizedMap(new HashMap<>());

  @Override
  public boolean isApplicable(SModule module) {
    if (module.isPackaged()) {
      return false;
    }
    // PROVISIONAL, I'd like to test it for solutions first, then would expand to other module kinds.
    if (false == module instanceof Solution) {
      return false;
    }
    return true;
  }

  @Override
  public void refreshModule(SModule module) {
    SModelReference modelReference = getModelReference(module);
    DescriptorModel dm = myModels.get(modelReference);
    if (dm != null) {
      dm.invalidate();
    } else {
      dm = new DescriptorModel(modelReference, module);
      dm.addEngagedOnGenerationLanguage(BootstrapLanguages.getLanguageDescriptorLang());
      myModels.put(modelReference, dm);
      ((SModuleBase) module).registerModel(dm);
    }
  }

  @Override
  public void forgetModule(SModule module) {
    SModelReference modelReference = getModelReference(module);
    // FIXME copy explanation why module.unregisterModel goes in front of myModels.remove from GeneratorDescriptorModelProvider#forgetModule
    DescriptorModel dm = myModels.get(modelReference);
    if (dm != null) {
      assert dm.getModule() == module;
      ((SModuleBase) module).unregisterModel(dm);
      myModels.remove(modelReference);
    }
  }

  @Override
  public void dispose() {
    ArrayList<DescriptorModel> models = new ArrayList<>(myModels.values());
    myModels.clear();
    for (DescriptorModel m : models) {
      SModule module = m.getModule();
      // FIXME how come I'm sure there's model write for dispose()?
      ((SModuleBase) module).unregisterModel(m);
    }
  }

  private SModelReference getModelReference(SModule module) {
    String moduleName = module.getModuleName();
//    int sharpIndex = moduleName.indexOf('#');
//    if (sharpIndex != -1) {
//      moduleName = moduleName.substring(0, sharpIndex);
//    }
    // name of the model has to match activator class name we would use when deployed module is loaded
    // XXX perhaps, we can have activator class name as module attribute (in JavaModuleFacet or a dedicated 'MPS-managed module' to account for scenario
    // when a module is just to produce some java code)
    // Besides, activator class name could be a property of node<Activator> that resides in @descriptor model
    return new jetbrains.mps.smodel.SModelReference(module.getModuleReference(), myDescriptorModelId, new SModelName(moduleName, SModelStereotype.DESCRIPTOR));
  }

  static class DescriptorModel extends TrivialModelDescriptor /*implements GeneratableSModel*/ {
    private final SModule myModule;
    private String myHash;

    DescriptorModel(SModelReference modelReference, SModule module) {
      super(new SnapshotModelData(modelReference));
      myModule = module;
    }

    /*package*/ void invalidate() {

    }
  }

}

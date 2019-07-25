/*
 * Copyright 2003-2016 JetBrains s.r.o.
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

import com.intellij.openapi.ui.Messages;
import jetbrains.mps.module.ReloadableModule;
import jetbrains.mps.project.dependency.VisibilityUtil;
import jetbrains.mps.smodel.Language;
import jetbrains.mps.smodel.SModelInternal;
import jetbrains.mps.smodel.adapter.MetaAdapterByDeclaration;
import jetbrains.mps.smodel.tempmodel.TemporaryModels;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.mps.openapi.language.SLanguage;
import org.jetbrains.mps.openapi.model.SModel;
import org.jetbrains.mps.openapi.model.SModelReference;
import org.jetbrains.mps.openapi.module.SModule;
import org.jetbrains.mps.openapi.module.SModuleReference;

import java.awt.Component;
import java.util.ArrayList;
import java.util.List;

/**
 * Utility to add imports to a model.
 * This class doesn't manage read/write access to a model, it's responsibility of a caller.
 * FIXME may show UI confirmation dialog from within a command/write action, is it good?
 * Now it's up to caller to decide whether to {@link #prepare(SModelReference) prepare} the importer from distinct model access,
 * then, if {@link #affectsModuleDependencies()} necessary}, to show {@link #confirmModuleChanges(Component) confirmation}, and
 * eventually {@link #execute() execute} in command, although I admit resulting code would be ugly.
 *
 * @author Alex Pyshkin
 * @author Artem Tikhomirov
 */
public class ModelImporter {
  private final SModel myModel;
  private final List<Entry> myImports = new ArrayList<>();

  public ModelImporter(@NotNull SModel model) {
    myModel = model;
    // This class was designed to edit fully fledged model, if your scenario needs to check for
    // visibility/scope of module dependencies for free-floating model, consider passing SRepository here.
    assert model.getRepository() != null;
  }

  public void prepare(SModelReference modelRefToImport) {
    Entry e = analyzeImport(modelRefToImport);
    myImports.add(e);
  }

  /**
   * @return {@code true} if any model from {@link #prepare(SModelReference)} comes from a module not visible to that of our model,
   * {@code false} either if there's no module (i.e. nothing to affect) or all dependencies are visible.
   */
  public boolean affectsModuleDependencies() {
    for (Entry e : myImports) {
      if (e.affectsModule()) {
        return true;
      }
    }
    return false;
  }

  private Entry analyzeImport(final SModelReference modelRefToImport) {
    SModel modelToImport = modelRefToImport.resolve(myModel.getRepository());
    if (modelToImport == null) {
      throw new IllegalArgumentException(String.format("Bad model reference: %s", modelRefToImport));
    }
    if (myModel.getModule() == null) {
      // code below doesn't make sense if there's no module
      return new Entry(modelRefToImport);
    }
    SModule moduleToImport = modelToImport.getModule();

    if (VisibilityUtil.isVisible(myModel.getModule(), modelToImport) || moduleToImport == null) {
      return new Entry(modelRefToImport);
    }

    if (moduleToImport instanceof Language && myModel.getModule() instanceof Solution && ((Language) moduleToImport).isAccessoryModel(modelRefToImport)) {
      // this dubious condition traces back to https://youtrack.jetbrains.com/issue/MPS-17337
      // FIXME discussed with MM, it's just a quick way to fix common scenarios, there's no particular idea behind models in Solutions to get
      //       used languages, while models in language get module dependency. If we manage to get rid of accessory models (in a way that we
      //       generate stuff from them, and then reference this generated code), we could drop this. However, it doesn't look feasible
      //       to throw accessory models in a foreseeable future, nor it is practical (for a modeling environment it's odd to struggle throwing models away),
      //       and better option is to give a choice here (provided we pop up a dialog anyway) if user meant to use language or to reference model node.
      return new Entry(MetaAdapterByDeclaration.getLanguage((Language) moduleToImport));
    }
    return new Entry(modelRefToImport, moduleToImport.getModuleReference());
  }

  public void execute() {
    boolean shallReload = affectsModuleDependencies();
    // affectsModuleDependencies() == true implies myModel got a module, otherwise there'd be nothing to affect.
    for (Entry e : myImports) {
      e.addImport(myModel);
    }

    // Reload has meaning only for reloadable modules. Import itself does not depend on module type of model.
    if (shallReload && myModel.getModule() instanceof ReloadableModule) {
      ((ReloadableModule) myModel.getModule()).reload();
    }
  }

  /**
   * @return {@code true} if user confirmed changes in the module (or there's no need in such confirmation)
   */
  public boolean confirmModuleChanges(Component parentComponent) {
    if (!affectsModuleDependencies()) {
      return true;
    }
    if (TemporaryModels.isTemporary(myModel)) {
      // I have no idea why temporary models are handled here and not in the caller, left intact.
      return true;
    }
    StringBuilder sb = new StringBuilder();
    for (Entry e : myImports) {
      if (!e.affectsModule()) {
        continue;
      }
      sb.append(String.format("Model <b>%s</b> is owned by module <b>%s</b> which is not imported.<br/>",
          e.myModelToImport.getName(), e.myModuleDep.getModuleName()));
    }

    final String msg = String.format("<html>%s<br/>Do you want to add module imports automatically?</html>", sb.toString());
    // ok / cancel is much better than yes/no. One would read 'no' as 'no module imports, but proceed with model import',
    // while 'cancel' suggests whole operation would stop, an it's the way rest of the code behaves
    return Messages.showOkCancelDialog(parentComponent, msg, "Module Import", Messages.getQuestionIcon()) == Messages.OK;
  }

  private static class Entry {
    private final SModelReference myModelToImport;
    private final SModuleReference myModuleDep;
    // When language is specified, it means we import language's accessory model and thus need lang in dependencies
    // XXX although this is dubious, why not regular module dep?
    private final SLanguage myUsedLanguage;

    public Entry(SModelReference modelReference) {
      myModelToImport = modelReference;
      myModuleDep = null;
      myUsedLanguage = null;
    }

    // add model import and module dependency
    public Entry(SModelReference modelReference, SModuleReference moduleDependency) {
      myModelToImport = modelReference;
      myModuleDep = moduleDependency;
      myUsedLanguage = null;
    }

    // add used language only
    public Entry(SLanguage languageToUse) {
      myModelToImport = null;
      myUsedLanguage = languageToUse;
      myModuleDep = null;
    }

    public void addImport(SModel model) {
      if (myModelToImport != null) {
        ((SModelInternal) model).addModelImport(myModelToImport);
      }
      if (myModuleDep != null && model.getModule() instanceof AbstractModule) {
        ((AbstractModule) model.getModule()).addDependency(myModuleDep, false);
      }
      if (myUsedLanguage != null) {
        ((SModelInternal) model).addLanguage(myUsedLanguage);
      }
    }

    public boolean affectsModule() {
      return myModuleDep != null;
    }
  }
}

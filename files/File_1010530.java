/*
 * Copyright 2003-2019 JetBrains s.r.o.
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

import jetbrains.mps.extapi.model.GeneratableSModel;
import jetbrains.mps.extapi.module.SModuleBase;
import jetbrains.mps.generator.ModelDigestUtil;
import jetbrains.mps.project.persistence.LanguageDescriptorPersistence;
import jetbrains.mps.smodel.BootstrapLanguages;
import jetbrains.mps.smodel.Language;
import jetbrains.mps.smodel.SModelId.IntegerSModelId;
import jetbrains.mps.smodel.SModelStereotype;
import jetbrains.mps.smodel.SnapshotModelData;
import jetbrains.mps.smodel.TrivialModelDescriptor;
import jetbrains.mps.smodel.language.LanguageAspectSupport;
import jetbrains.mps.smodel.language.LanguageRegistry;
import jetbrains.mps.smodel.language.LanguageRegistryListener;
import jetbrains.mps.smodel.language.LanguageRuntime;
import jetbrains.mps.util.JDOMUtil;
import jetbrains.mps.util.MacrosFactory;
import jetbrains.mps.vfs.IFile;
import org.apache.log4j.Logger;
import org.jdom.Document;
import org.jdom.Element;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.mps.openapi.event.SNodeAddEvent;
import org.jetbrains.mps.openapi.event.SNodeRemoveEvent;
import org.jetbrains.mps.openapi.language.SLanguage;
import org.jetbrains.mps.openapi.model.EditableSModel;
import org.jetbrains.mps.openapi.model.SModel;
import org.jetbrains.mps.openapi.model.SModelId;
import org.jetbrains.mps.openapi.model.SModelName;
import org.jetbrains.mps.openapi.model.SModelReference;
import org.jetbrains.mps.openapi.model.SNodeChangeListenerAdapter;
import org.jetbrains.mps.openapi.module.SModule;

import java.io.ByteArrayOutputStream;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Contributes '@descriptor' model to Language modules.
 */
public class LanguageDescriptorModelProvider extends DescriptorModelProvider {
  private final static SModelId ourDescriptorModelId = new IntegerSModelId(0x0f010101);

  private final Map<SModelReference, LanguageModelDescriptor> myModels = new ConcurrentHashMap<>();
  private final LanguageRegistry myLanguageRegistry;
  private final RootChangeListener myListener = new RootChangeListener();

  private class RootChangeListener extends SNodeChangeListenerAdapter {
    private final Set<SModelReference> myListenedModels = new HashSet<>();

    public void attach(SModule module) {
      for (SModel model : module.getModels()) {
        if (model instanceof EditableSModel && LanguageAspectSupport.isAspectModel(model)) {
          if (myListenedModels.add(model.getReference())) {
            model.addChangeListener(this);
          }
        }
      }
    }

    public void detach(SModule module) {
      // doesn't hurt to remove a listener even if we didn't add it
      for (SModel m : module.getModels()) {
        myListenedModels.remove(m.getReference());
        m.removeChangeListener(this);
      }
    }

    @Override
    public void nodeAdded(@NotNull SNodeAddEvent event) {
      if (!event.isRoot()) {
        return;
      }
      Language language = Language.getLanguageFor(event.getModel());
      if (language != null) {
        refreshModule(language,true);
      }
    }

    @Override
    public void nodeRemoved(@NotNull SNodeRemoveEvent event) {
      if (!event.isRoot()) {
        return;
      }
      Language language = Language.getLanguageFor(event.getModel());
      if (language != null) {
        refreshModule(language,true);
      }
    }
  }

  private final LanguageRegistryListener myAspectReloadListener = new LanguageRegistryListener() {
    @Override
    public void beforeLanguagesUnloaded(Iterable<LanguageRuntime> languages) {
      // no-op
    }

    @Override
    public void afterLanguagesLoaded(Iterable<LanguageRuntime> languages) {
      HashSet<SLanguage> loadedLanguages = new HashSet<>(100);
      for (LanguageRuntime lr : languages) {
        loadedLanguages.add(lr.getIdentity());
      }
      // check if any language module we track needs any of the reloaded languages. I don't care about language modules I don't track yet.
      // Here, I assume that aspect model lists main/auxiliary languages of its aspect as 'used languages' and they are propagated up
      // to module's used language. If there's a chance to have an aspect model that doesn't manifest its used languages, please let me know.
      // The fact that I can encounter used languages of descriptor model itself doesn't bother me here, I just care to signal 'need to rebuild' event,
      // it doesn't hurt if I refresh a bit more than utterly necessary.
      myModels.forEach((mr, lmd) -> {
        Set<SLanguage> moduleUsedLanguages = lmd.getModule().getUsedLanguages();
        if (!Collections.disjoint(moduleUsedLanguages, loadedLanguages)) {
          lmd.updateGenerationLanguages();
        }
      });
    }
  };

  public LanguageDescriptorModelProvider(LanguageRegistry languageRegistry) {
    myLanguageRegistry = languageRegistry;
    myLanguageRegistry.addRegistryListener(myAspectReloadListener);
  }

  @Override
  public void dispose() {
    myLanguageRegistry.removeRegistryListener(myAspectReloadListener);
    removeAll();
  }

  /**
   * We don't care to supply descriptor model for deployed modules as there's no use for language descriptor there
   */
  @Override
  public boolean isApplicable(SModule module) {
    return module instanceof Language && !module.isPackaged();
  }

  @Override
  public void forgetModule(SModule language) {
    myListener.detach(language);
    Language module = (Language) language;
    SModelReference ref = getSModelReference(module);
    LanguageModelDescriptor descriptor = myModels.remove(ref);
    if (descriptor != null) {
      removeModel(descriptor);
    }
  }

  @Override
  public void refreshModule(SModule language) {
    refreshModule(language,false);
  }

  public void refreshModule(SModule language,boolean nodeChange) {
    myListener.attach(language);
    Language module = (Language) language;
    SModelReference ref = getSModelReference(module);
    if (!myModels.containsKey(ref)) {
      createModel(ref, module);
    } else {
      if (!nodeChange){
        myModels.get(ref).updateGenerationLanguages();
      }
      LanguageModelDescriptor languageModelDescriptor = myModels.get(ref);
      if (languageModelDescriptor != null) {
        languageModelDescriptor.invalidate();
      }
    }
  }

  private void removeAll() {
    List<LanguageModelDescriptor> models = new ArrayList<>(myModels.values());
    for (LanguageModelDescriptor model : models) {
      removeModel(model);
    }
    myModels.clear();
  }

  private void removeModel(LanguageModelDescriptor md) {
    SModule module = md.getModule();
    if (module instanceof SModuleBase) {
      ((SModuleBase) module).unregisterModel(md);
    }
  }

  public LanguageModelDescriptor createModel(SModelReference ref, @NotNull Language module) {
    LanguageModelDescriptor result = new LanguageModelDescriptor(ref, module);
    result.updateGenerationLanguages();

    myModels.put(ref, result);
    module.registerModel(result);
    return result;
  }

  /*package*/ static SModelReference getSModelReference(Language module) {
    return new jetbrains.mps.smodel.SModelReference(module.getModuleReference(), ourDescriptorModelId, new SModelName(module.getModuleName(), SModelStereotype.DESCRIPTOR));
  }

  public String toString() {
    return "component: Language Descriptor Models Provider";
  }

  public static final class LanguageModelDescriptor extends TrivialModelDescriptor implements GeneratableSModel {
    private final Language myModule;
    private String myHash;
    /*
     * Module file keeps closure of its dependencies, and the change in the closure is not propagated as a module changed event.
     * (e.g. if used devkit got new exported solution, version of the solution module is recorded under dependencyVersions tag)
     * Without module change, hash has not been re-calculated and no 'generation required' status show up. To mitigate,
     * record timestamp of a module file the moment hash is calculated.
     */
    private long myHashTimestamp;

    private LanguageModelDescriptor(SModelReference ref, Language module) {
      super(new SnapshotModelData(ref));
      myModule = module;
      myHash = null;
    }

    /**
     * FIXME
     * adding used languages to descriptor model is a hack,
     * fixing that the runtime solutions of languages engaged on generations are ignored at compilation
     */
    void updateGenerationLanguages() {
      jetbrains.mps.smodel.SModel m = getSModel();
      m.addDevKit(BootstrapLanguages.getLanguageDescriptorDevKit());
      m.addEngagedOnGenerationLanguage(BootstrapLanguages.getLanguageDescriptorLang());
      Set<SLanguage> importsToRemove = new HashSet<>(m.usedLanguages()); // calculating the delta
      Set<SLanguage> importsToAdd = new HashSet<>();
      Collection<SModel> aspectModels = LanguageAspectSupport.getAspectModels(myModule);
      for (SModel aspect : aspectModels) {
        Collection<SLanguage> mainLanguages = new ArrayList<>(LanguageAspectSupport.getMainLanguages(aspect));
        mainLanguages.addAll(LanguageAspectSupport.getDefaultDevkitLanguages(aspect));
        for (@NotNull SLanguage aspectLanguage : mainLanguages) {
          addEngagedOnGenerationLanguage(aspectLanguage);
          importsToRemove.remove(aspectLanguage);
          importsToAdd.add(aspectLanguage);
        }
      }
      importsToAdd.removeAll(m.usedLanguages()); // not adding the same language again
      importsToRemove.forEach(m::deleteLanguage); // applying calculated delta
      importsToAdd.forEach(m::addLanguage);
      for (SLanguage lang : m.usedLanguages()) {
        int versionFromModule = myModule.getUsedLanguageVersion(lang, false);
        if (m.getLanguageImportVersion(lang) != versionFromModule) {
          m.setLanguageImportVersion(lang, versionFromModule);
        }
      }
    }

    @Override
    public boolean isGeneratable() {
      return !myModule.isReadOnly();
    }

    @Override
    public boolean isGenerateIntoModelFolder() {
      return false;
    }

    @Override
    public void setGenerateIntoModelFolder(boolean value) {
      throw new UnsupportedOperationException();
    }

    @Override
    public String getModelHash() {
      String hash = myHash;
      IFile descriptorFile = myModule.getDescriptorFile();
      long hashTimestamp = descriptorFile.lastModified();
      if (hash != null && hashTimestamp == myHashTimestamp) {
        return hash;
      }


      try {
        ByteArrayOutputStream output = new ByteArrayOutputStream();
        Element xmlElement = new LanguageDescriptorPersistence(MacrosFactory.forModuleFile(descriptorFile)).save(myModule.getModuleDescriptor());
        JDOMUtil.writeDocument(new Document(xmlElement), output);
        hash = ModelDigestUtil.hashText(output.toString());
      } catch (Exception ex) {
        Logger.getLogger(LanguageDescriptorModelProvider.class).error("Failed to detect changes in a module descriptor", ex);
        return null;
      }

      BigInteger modelHash = new BigInteger(hash, Character.MAX_RADIX);
      for (SModel aspModel : LanguageAspectSupport.getAspectModels(myModule)) {
        if (aspModel instanceof EditableSModel && !((EditableSModel) aspModel).isChanged() && aspModel instanceof GeneratableSModel) {
          final String h = ((GeneratableSModel) aspModel).getModelHash();
          if (h != null) {
            modelHash = modelHash.xor(new BigInteger(h, Character.MAX_RADIX));
          }
        }
      }

      hash = modelHash.toString(Character.MAX_RADIX);
      myHash = hash;
      myHashTimestamp = hashTimestamp;
      return hash;
    }

    @Override
    public void setDoNotGenerate(boolean value) {
      throw new UnsupportedOperationException();
    }

    @Override
    public boolean isDoNotGenerate() {
      return false;
    }

    public void invalidate() {
      if (getSModel().isDisposed()) {
        // SModelBase.detach() dispose a model, but doesn't null the reference.
        // When we delete a language module, models are deleted one by one, and if @descriptor is deleted first,
        // beforeRemove(other models) fails with NPE on update to change reference of disposed model
        // Not sure though, if it's the right approach, if we won't get to invalidate() with disposed descriptor, but
        // there is a need to re-init descriptor model.
        return;
      }
      changeModelReference(getSModelReference(myModule));
      myHash = null;
    }
  }
}

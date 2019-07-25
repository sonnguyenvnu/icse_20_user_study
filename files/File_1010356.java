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
package jetbrains.mps.smodel;

import jetbrains.mps.extapi.module.SRepositoryExt;
import jetbrains.mps.module.ReloadableModule;
import jetbrains.mps.module.ReloadableModuleBase;
import jetbrains.mps.module.SDependencyImpl;
import jetbrains.mps.project.DescriptorTargetFileAlreadyExistsException;
import jetbrains.mps.project.facets.TestsFacet;
import jetbrains.mps.project.io.DescriptorIO;
import jetbrains.mps.project.io.DescriptorIOFacade;
import jetbrains.mps.project.structure.modules.GeneratorDescriptor;
import jetbrains.mps.project.structure.modules.LanguageDescriptor;
import jetbrains.mps.project.structure.modules.ModuleDescriptor;
import jetbrains.mps.smodel.adapter.structure.MetaAdapterFactory;
import jetbrains.mps.smodel.language.LanguageAspectSupport;
import jetbrains.mps.util.IterableUtil;
import jetbrains.mps.util.annotation.ToRemove;
import jetbrains.mps.vfs.IFile;
import org.apache.log4j.Logger;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.jetbrains.mps.openapi.language.SLanguage;
import org.jetbrains.mps.openapi.model.SModel;
import org.jetbrains.mps.openapi.model.SModelReference;
import org.jetbrains.mps.openapi.model.SNode;
import org.jetbrains.mps.openapi.module.SDependency;
import org.jetbrains.mps.openapi.module.SDependencyScope;
import org.jetbrains.mps.openapi.module.SModule;
import org.jetbrains.mps.openapi.module.SModuleReference;
import org.jetbrains.mps.openapi.module.SRepository;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class Language extends ReloadableModuleBase implements ReloadableModule {

  /**
   * Default, although not mandatory location we save our models to.
   * Made public just for the sake of tests.
   */
  public static final String LANGUAGE_MODELS = "models";
  /**
   * @deprecated Use of default value to detect aspect source root or to check module existence is wrong.
   */
  @Deprecated
  @ToRemove(version = 3.3)
  public static final String LEGACY_LANGUAGE_MODELS = "languageModels";

  @NotNull private LanguageDescriptor myLanguageDescriptor;
  // modifications are guarded with model write lock, assertCanChange()
  private final List<Generator> myAttachedGenerators = new ArrayList<>(2);

  protected Language(@NotNull LanguageDescriptor descriptor, @Nullable IFile file) {
    super(file);
    myLanguageDescriptor = descriptor;
    setModuleReference(descriptor.getModuleReference());
  }

  @Override
  public void reloadAfterDescriptorChange() {
    super.reloadAfterDescriptorChange();
    revalidateGenerators();
  }

  public void addExtendedLanguage(@NotNull SModuleReference langRef) {
    if (myLanguageDescriptor.getExtendedLanguages().contains(langRef)) return;
    LanguageDescriptor moduleDescriptor = getModuleDescriptor();
    moduleDescriptor.getExtendedLanguages().add(langRef);

    dependenciesChanged();
    setChanged();

    fireChanged();
  }

  public Set<SModuleReference> getExtendedLanguageRefs() {
    HashSet<SModuleReference> res = new HashSet<>(myLanguageDescriptor.getExtendedLanguages());
    if (!BootstrapLanguages.coreLanguageRef().equals(getModuleReference())) {
      //this is needed now as we don't force the user to have an explicit dependency on core
      res.add(BootstrapLanguages.coreLanguageRef());
    }
    return res;
  }

  @Override
  public Iterable<SDependency> getDeclaredDependencies() {
    HashSet<SDependency> rv = new HashSet<>(IterableUtil.asCollection(super.getDeclaredDependencies()));
    final SRepository repo = getRepository();
    for (SModuleReference language : getExtendedLanguageRefs()) {
      // XXX not clear whether it's worth including implicit "extends lang.core" (see getExtendedLanguageRefs())
      // or adhere to 'declared' in the name of getDeclaredDependencies and use myLanguageDescriptor.getExtendedLanguages() only
      rv.add(new SDependencyImpl(language, repo, SDependencyScope.EXTENDS, true));
    }
    return rv;
  }

  /**
   * All the language modules extended by this one within the same repository this module is attached to.
   * For detached module, the set returned is empty. To access 'raw' information about extended languages,
   * one could use {@link #getExtendedLanguageRefs()}.
   *
   * This method requires model read access as it resolves modules.
   *
   * IMPORTANT: if any extended language is missing from the repository of the module, it's simply ignored and not included into outcome
   * (nor the closure of its extended languages).
   *
   * NOTE, implementation hides cyclic dependencies between languages, e.g if "A extends B extends A",
   * you'd get "A extends B" for A and "B extends A" for B.
   */
  @NotNull
  public Set<Language> getAllExtendedLanguages() {
    HashSet<Language> languages = new HashSet<>();
    final SRepository repository = getRepository();
    if (repository == null) {
      return languages;
    }

    ArrayDeque<Language> queue = new ArrayDeque<>();
    queue.add(this);

    do {
      Language current = queue.poll();
      if (!languages.add(current)) {
        continue;
      }
      for (SModuleReference lr : current.getExtendedLanguageRefs()) {
        final SModule l = lr.resolve(repository);
        if (l instanceof Language) {
          queue.add((Language) l);
        }
      }
    } while (!queue.isEmpty());

    return languages;
  }

  public Collection<SModuleReference> getRuntimeModulesReferences() {
    return Collections.unmodifiableSet(myLanguageDescriptor.getRuntimeModules());
  }

  public void validateExtends() {
    List<SModuleReference> remove = new ArrayList<>();
    for (SModuleReference ref : myLanguageDescriptor.getExtendedLanguages()) {
      if (getModuleName().equals(ref.getModuleName())) {
        remove.add(ref);
      }
    }

    if (!remove.isEmpty()) {
      myLanguageDescriptor.getExtendedLanguages().removeAll(remove);
      setChanged();
    }
  }

  @Override
  public void onModuleLoad() {
    super.onModuleLoad();
    validateExtends();
  }

  /*
   * Update repository generator modules associated with this language with descriptors known to the language (registers new generators, if necessary)
   * This is another place in addition to ModulesMiner that knows about language-generator MD containment
   */
  private void revalidateGenerators() {
    // Fair implementation shall deal with getOwnedGenerators() only, however, at the moment, Generator module needs its source Language module
    // and it's tricky to write external code that would deal with standalone/external generators when language's MD changes (there's no proper notification
    // mechanism or anything else to react to MD change). That's why we control all generators associated with the language here.
    //
    // Another important note here is that getOwnedGenerators() in its present state may not give proper answer (e.g. if a changed MD doesn't list a generator
    // module that has been previously exposed
    LinkedList<Generator> existingGenerators = new LinkedList<>(getGenerators());

    SRepositoryExt moduleRepository = (SRepositoryExt) getRepository();
    ModuleRepositoryFacade mrf = new ModuleRepositoryFacade(moduleRepository);
    for (GeneratorDescriptor nextDescriptor : myLanguageDescriptor.getGenerators()) {
      Generator nextGenerator = null;
      for (Iterator<Generator> it = existingGenerators.iterator(); it.hasNext(); ) {
        // looking for the existing generator with same ID
        Generator nextGeneratorCandidate = it.next();
        GeneratorDescriptor nextGeneratorCandidateDescriptor = nextGeneratorCandidate.getModuleDescriptor();
        if (Objects.equals(nextGeneratorCandidateDescriptor.getNamespace(), nextDescriptor.getNamespace()) &&
            Objects.equals(nextGeneratorCandidateDescriptor.getId(), nextDescriptor.getId())) {
          nextGenerator = nextGeneratorCandidate;
          it.remove();
          break;
        }
      }

      if (nextGenerator != null) {
        nextGenerator.updateGeneratorDescriptor(nextDescriptor);
      } else {
        // new generator is registered with the same owners as this language
        // at the moment, we may use old cons that doesn't take explicit descriptor file (as it uses the one from this language, which is what we need there),
        // but it's fun to try a new one
        Generator generator = new Generator(MetaAdapterFactory.getLanguage(getModuleReference()), nextDescriptor, getDescriptorFile(), this);
        for (MPSModuleOwner moduleOwner : mrf.getModuleOwners(this)) {
          moduleRepository.registerModule(generator, moduleOwner);
        }
      }
    }
    // stale generator modules are unregistered from all owners
    // here we assume generator modules could not exist without their language (which is true now provided Generator cons takes Language instance)
    // therefore we unregister even generator modules the language doesn't own (see existingGenerators initialization, above).
    // FIXME has to be reviewed for standalone generators. With SLanguage instead of source language module, we might want to let them be as is.
    //       Alternatively, may use some package-local initialization method to pass new language module instance there.
    for (Generator stale : existingGenerators) {
      mrf.unregisterModule(stale);
    }
  }

  @Override
  public void dispose() {
    // though MPSModuleRepository.doUnregisterModule() cares to unregister language's generators properly, seems it
    // doesn't hurt to try to unregister them here as well. Either it would end up as no-op for an empty collection, or would keep
    // repository consistent (in case dispose() has been reached not through MPSModuleRepository)
//    final SRepository repo = getRepository();
//    if (repo != null) {
//      final ModuleRepositoryFacade mrf = new ModuleRepositoryFacade(repo);
//      // see revalidateGenerators(), above, for reasons why we unregister all associated generators, not only directly owned.
//      getGenerators().forEach(mrf::unregisterModule);
//    }
    super.dispose();
  }

  @NotNull
  @Override
  public LanguageDescriptor getModuleDescriptor() {
    return myLanguageDescriptor;
  }

  @Override
  public void doSetModuleDescriptor(ModuleDescriptor moduleDescriptor) {
    assert moduleDescriptor instanceof LanguageDescriptor;
    myLanguageDescriptor = (LanguageDescriptor) moduleDescriptor;
    SModuleReference reference = new jetbrains.mps.project.structure.modules.ModuleReference(myLanguageDescriptor.getNamespace(), myLanguageDescriptor.getId());
    setModuleReference(reference);
    if (getRepository() instanceof MPSModuleRepository) {
      ((MPSModuleRepository) getRepository()).invalidateCaches();
    }
  }

  // fixme: remove, use #setModuleDescriptor instead
  @Deprecated
  public void setLanguageDescriptor(@NotNull final LanguageDescriptor moduleDescriptor) {
    setModuleDescriptor(moduleDescriptor);
  }

  public int getLanguageVersion() {
    return getModuleDescriptor().getLanguageVersion();
  }

  public void setLanguageVersion(int version) {
    getModuleDescriptor().setLanguageVersion(version);
    fireChanged();
    setChanged();
  }

  /**
   * @return all generators that treat this language as their source one.
   */
  public Collection<Generator> getGenerators() {
    assertCanRead();
    // Language module now tracks Generator modules it is owner to. Generator modules, once attached to a repo, tell their source language they are here
    // with #register(Generator), and tell they are gone with #unregister(Generator).
    return new ArrayList<>(myAttachedGenerators);
  }

  /**
   * PROVISIONAL API, DON'T USE UNLESS YOU'RE 100% SURE WHAT IS THE REASON FOR THAT, AND WHAT'S THE (UPCOMING) DIFFERENCE WITH {@link #getGenerators()}
   * NOTE: BE CAREFUL WHEN INVOKED FROM A CODE THAT REACTS TO MODULE DESCRIPTOR CHANGES
   *       if invoked with a changed MD, gives state according to MD contents, and not the one exposed in the repository (think about scenario when
   *       a repo-registered, language-owned generator has been removed from MD. This method would give empty set despite the fact generator module is there)
   * @return generators declared and controlled by this language module.
   */
  public Collection<Generator> getOwnedGenerators() {
    Set<SModuleReference> ownedGenerators = getModuleDescriptor().getGenerators().stream().map(ModuleDescriptor::getModuleReference).collect(Collectors.toSet());
    return getGenerators().stream().filter(g -> ownedGenerators.contains(g.getModuleReference())).collect(Collectors.toList());
  }

  @Override
  public void rename(@NotNull String newModuleName) throws DescriptorTargetFileAlreadyExistsException {
    for (Generator g : getOwnedGenerators()) {
      g.rename(newModuleName);
    }
    super.rename(newModuleName);
  }

  /**
   * @deprecated method is not bad per se (Language module could tell SNode with concept declaration. However,
   *            it silently excludes Interface concepts, and likely its uses need attention and switch to SConcept.
   *            Then, we could decide whether we truly need access to language's concept nodes this way, or shall use
   *            LanguageAspects instead.
   */
  @Deprecated
  @ToRemove(version = 3.4)
  public List<SNode> getConceptDeclarations() {
    // FIXME there are uses in mbeddr
    SModel structureModel = getStructureModelDescriptor();
    if (structureModel == null) return Collections.emptyList();
    return FastNodeFinderManager.get(structureModel).getNodes(SNodeUtil.concept_ConceptDeclaration, true);
  }

  public List<SModel> getUtilModels() {
    Set<SModel> models = new HashSet<>(getModels());
    models.removeAll(LanguageAspectSupport.getAspectModels(this));
    models.removeAll(getAccessoryModels());

    List<SModel> result = new ArrayList<>(models.size());
    for (SModel md : models) {
      if (SModelStereotype.isStubModel(md) || SModelStereotype.isDescriptorModel(md)) {
        // perhaps, we need more generic isPredefinedStereotypeMPS()
        continue;
      }
      result.add((md));
    }
    return result;
  }

  public SModel getStructureModelDescriptor() {
    return LanguageAspect.STRUCTURE.get(this);
  }

  /**
   * fixme why generator saves language??
   * generator is contained in language it must be the other way around!
   */
  @Override
  public void save() {
    super.save();
    if (isReadOnly()) return;

    if (myLanguageDescriptor.getLoadException() != null){
      return;
    }

    try {
      DescriptorIO<LanguageDescriptor> io = DescriptorIOFacade.getInstance().standardProvider().languageDescriptorIO();
      io.writeToFile(getModuleDescriptor(), getDescriptorFile());
    } catch (Exception ex) {
      Logger.getLogger(getClass()).error("Save failed", ex);
    }
  }

  public List<SModel> getAccessoryModels() {
    List<SModel> result = new LinkedList<>();
    for (SModelReference model : getModuleDescriptor().getAccessoryModels()) {
      SModel modelDescriptor = model.resolve(getRepository());
      if (modelDescriptor != null) {
        result.add(modelDescriptor);
      }
    }
    return result;
  }

  public boolean isAccessoryModel(org.jetbrains.mps.openapi.model.SModelReference modelReference) {
    return myLanguageDescriptor.getAccessoryModels().stream().anyMatch(Predicate.isEqual(modelReference));
  }

  public void removeAccessoryModel(org.jetbrains.mps.openapi.model.SModel sm) {
    // XXX why removal of accessory model is not done through ModuleDescriptor as other editing activities?
    //     i.e. module properties add accessory models through MD, but remove them through Language, which is odd.
    final SModelReference accessoryModelRef = sm.getReference();
    boolean changed = myLanguageDescriptor.getAccessoryModels().removeIf(accessoryModelRef::equals);
    if (changed) {
      // XXX Perhaps, setModuleDescriptor is too much, as it fires changed + dependenciesChange and eventually reloads classes,
      //     while change in accessory models unlikely to affect any compiled class.
      //     I'd stick to setChanged(true) + fireChanged() here, instead.
      setModuleDescriptor(myLanguageDescriptor);
    }
  }

  public String toString() {
    return getModuleName() + " [language]";
  }

  @Deprecated
  @ToRemove(version = 3.3)
  //no full equivalent to this method, use appropriate method from LanguageAspectSupport
  private LanguageAspect getAspectForModel(@NotNull org.jetbrains.mps.openapi.model.SModel sm) {
    for (LanguageAspect la : LanguageAspect.values()) {
      if (la.get(this) == sm) {
        return la;
      }
    }
    return null;
  }

  public static Language getLanguageForLanguageAspect(org.jetbrains.mps.openapi.model.SModel modelDescriptor) {
    return getLanguageFor(modelDescriptor);
  }

  @Deprecated
  @ToRemove(version = 3.3)
  //no full equivalent to this method, use appropriate method from LanguageAspectSupport
  //no usages in MPS, 4 uses in mbeddr
  @Nullable
  public static LanguageAspect getModelAspect(org.jetbrains.mps.openapi.model.SModel sm) {
    if (sm == null) return null;
    SModule module = sm.getModule();
    if (!(module instanceof Language)) {
      return null;
    }

    Language l = (Language) module;
    return l.getAspectForModel(sm);
  }

  public static boolean isLanguageOwnedAccessoryModel(org.jetbrains.mps.openapi.model.SModel sm) {
    SModule modelOwner = sm.getModule();
    if (modelOwner instanceof Language) {
      Language l = (Language) modelOwner;
      return l.isAccessoryModel(sm.getReference());
    }
    return false;
  }

  public static Language getLanguageFor(org.jetbrains.mps.openapi.model.SModel sm) {
    SModule owner = sm.getModule();
    if (owner instanceof Language) {
      return (Language) owner;
    }
    return null;
  }

  @Override
  protected void collectMandatoryFacetTypes(Set<String> types) {
    super.collectMandatoryFacetTypes(types);
    types.add(TestsFacet.FACET_TYPE);
  }

  // TODO
//  @Nullable
//  @Override
//  public Language clone(String targetRoot, String targetNamespace) {
//    LanguageDescriptor targetDescriptor = new LanguageDescriptor();
//    IFile targetDescriptorFile = getFileSystem().getFile(targetRoot + File.separator + targetNamespace + MPSExtentions.DOT_LANGUAGE);
//
//    targetDescriptor.setId(ModuleId.regular());
//    targetDescriptor.setNamespace(targetNamespace);
//    getModuleDescriptor().cloneTo(targetDescriptor, PathConverters.forDescriptorFiles(targetDescriptorFile, getDescriptorFile()));
//    LanguageDescriptorPersistence.saveLanguageDescriptor(targetDescriptorFile, targetDescriptor, MacrosFactory.forModuleFile(targetDescriptorFile));
//
//    Language targetLanguage = new Language(targetDescriptor, targetDescriptorFile);
//    ModelRootCloneUtil.cloneModelRootsTo(getModelRoots(), targetLanguage);
//
//    Iterator<Generator> targetGenerators = targetLanguage.getGenerators().iterator();
//    for (Generator generator : getGenerators()) {
//      Generator targetGenerator = targetGenerators.next();
//      ModelRootCloneUtil.cloneModelRootsTo(generator.getModelRoots(), targetGenerator);
//    }
//
//    FIXME RADIMIR rename models here
//
//    return targetLanguage;
//  }


  /*package*/ void register(@NotNull Generator generator) {
    assertCanChange();
    myAttachedGenerators.add(generator);
  }
  /*package*/ void unregister(@NotNull Generator generator) {
    assertCanChange();
    if (!myAttachedGenerators.remove(generator)) {
      throw new IllegalStateException(String.format("Generator %s has not been previously registered with the language %s", generator.getModuleName(), getModuleName()));
    }
  }

  public static class LanguageModelsAutoImports extends jetbrains.mps.project.ModelsAutoImportsManager.AutoImportsContributor {
    @Override
    public boolean isApplicable(SModule module) {
      return module instanceof Language;
    }

    @NotNull
    @Override
    public Collection<SLanguage> getLanguages(SModule contextModule, SModel model) {
      return LanguageAspectSupport.getMainLanguages(model);
    }

    @Override
    public Collection<SModuleReference> getDevKits(SModule contextModule, SModel forModel) {
      Collection<SModuleReference> initialDevKits = new ArrayList<>(LanguageAspectSupport.getInitialDevKits(forModel));
      SModuleReference defaultDevkit = LanguageAspectSupport.getDefaultDevkit(forModel);
      if(defaultDevkit != null) {
        initialDevKits.add(defaultDevkit);
      }
      if (!initialDevKits.isEmpty()) {
        return initialDevKits;
      }
      return Collections.singleton(BootstrapLanguages.getGeneralPurposeDevKit());
    }
  }
}

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
package jetbrains.mps.persistence;

import jetbrains.mps.extapi.persistence.CopyNotSupportedException;
import jetbrains.mps.extapi.persistence.CopyableModelRoot;
import jetbrains.mps.extapi.persistence.DefaultSourceRoot;
import jetbrains.mps.extapi.persistence.FileBasedModelRoot;
import jetbrains.mps.extapi.persistence.FileDataSource;
import jetbrains.mps.extapi.persistence.ModelFactoryRegistry;
import jetbrains.mps.extapi.persistence.ModelFactoryService;
import jetbrains.mps.extapi.persistence.SourceRoot;
import jetbrains.mps.extapi.persistence.SourceRootKind;
import jetbrains.mps.extapi.persistence.SourceRootKinds;
import jetbrains.mps.extapi.persistence.datasource.DataSourceFactoryFromName;
import jetbrains.mps.extapi.persistence.datasource.DataSourceFactoryFromURL;
import jetbrains.mps.extapi.persistence.datasource.DataSourceFactoryRuleService;
import jetbrains.mps.extapi.persistence.datasource.PreinstalledDataSourceTypes;
import jetbrains.mps.persistence.DataSourceFactoryBridge.CompositeResult;
import jetbrains.mps.project.structure.model.ModelRootDescriptor;
import jetbrains.mps.util.FileUtil;
import jetbrains.mps.util.annotation.ToRemove;
import jetbrains.mps.vfs.IFile;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.jetbrains.mps.openapi.model.SModel;
import org.jetbrains.mps.openapi.model.SModelId;
import org.jetbrains.mps.openapi.model.SModelName;
import org.jetbrains.mps.openapi.persistence.DataSource;
import org.jetbrains.mps.openapi.persistence.ModelCreationException;
import org.jetbrains.mps.openapi.persistence.ModelFactory;
import org.jetbrains.mps.openapi.persistence.ModelFactoryType;
import org.jetbrains.mps.openapi.persistence.ModelLoadException;
import org.jetbrains.mps.openapi.persistence.ModelRoot;
import org.jetbrains.mps.openapi.persistence.ModelRootFactory;
import org.jetbrains.mps.openapi.persistence.datasource.DataSourceType;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

/**
 * This model root is responsible for loading models from the source roots
 * as well as for creating models and register them in itself.
 *
 * It looks for {@link DataSourceFactoryFromName} and {@link DataSourceFactoryFromURL} instances
 * through the {@link DataSourceFactoryRuleService} and
 * finds proper {@link ModelFactory} instances via the {@link ModelFactoryRegistry}
 * data source kind to model factory association.
 *
 * See a variety of model creation methods below.
 * See {@link #collectModels(SourceRoot)} for traversing logic of this model root.
 *
 * It is used by MPS to store all the kinds of models (except the java sources and classes stubs) -- therefore the poor naming.
 *
 * PLAN:
 * It makes sense to unite this concept with other file-system-based model root concepts.
 * Probably it is going to be transformed into a single {@code FileSystemModelRoot} entity which will be suitable for any
 * model storage system which has a tree-like storage.
 *
 * @author apyshkin
 * @author evgeny
 * @since 11/9/12
 */
public final class DefaultModelRoot extends FileBasedModelRoot implements CopyableModelRoot<DefaultModelRoot> {
  private static final Logger LOG = LogManager.getLogger(DefaultModelRoot.class);
  private final ModelFactoryRegistry myModelFactoryRegistry;
  private final DataSourceFactoryRuleService myDataSourceRegistry;

  /**
   * Use {@link ModelRootFactory#create()} to obtain instance of the class
   */
  /*package*/ DefaultModelRoot(ModelFactoryRegistry modelFactoryRegistry, DataSourceFactoryRuleService dsRegistry) {
    myModelFactoryRegistry = modelFactoryRegistry;
    myDataSourceRegistry = dsRegistry;
  }

  /**
   * Provisional way to instantiate DMR for specific scenario to create root descriptor.
   * DO NOT invoke methods that may require externally configured services/components.
   *
   * Unless {@link #createDescriptor(IFile, IFile...)} is re-written not to use DMR.save(),
   * and there are uses in MPS that access the method without MPS initialized.
   * IDEA plugin tests do that, which is somewhat legal as ModelRootDescriptor has to be
   * available w/o started MPS, though originally the code in
   * JpsTestModelsEnvironment.createModelRoot relied on DMR, which is wrong, although used to work)
   *
   */
  @ToRemove(version = 0)
  private DefaultModelRoot(int ignored) {
    myModelFactoryRegistry = null;
    myDataSourceRegistry = null;
  }

  @NotNull
  @Override
  public List<SourceRootKind> getSupportedFileKinds1() {
    return Collections.singletonList(SourceRootKinds.SOURCES);
  }

  @Override
  public String getType() {
    return PersistenceRegistry.DEFAULT_MODEL_ROOT;
  }

  @Override
  public SModel getModel(@NotNull SModelId id) {
    return getModule().getModel(id);
  }

  @NotNull
  @Override
  public Iterable<SModel> loadModels() {
    List<SourceRoot> sourceRoots = getSourceRoots(SourceRootKinds.SOURCES);
    if (sourceRoots.isEmpty()) {
      IFile contentDir = getContentDirectory();
      if (contentDir == null) {
        LOG.error(String.format("Bad model root (no content location nor sources) for module %s", getModule()));
      } else {
        LOG.warn(String.format("No source roots specified for location %s of module %s, no models were loaded", contentDir, getModule()));
      }
      return Collections.emptyList();
    }
    List<SModel> result = new ArrayList<>();
    for (SourceRoot sourceRoot : sourceRoots) {
      result.addAll(collectModels(sourceRoot));
    }
    return result;
  }

  @NotNull
  private Collection<SModel> collectModels(@NotNull SourceRoot sourceRoot) {
    List<SModel> result = new ArrayList<>();
    ModelSourceRootWalker modelSourceRootWalker = new ModelSourceRootWalker(this, (factory, dataSource, options, file) -> {
      try {
        SModel model = factory.load(dataSource, options.convertToLoadingOptions());
        result.add(model);
      } catch (ModelLoadException | IOException ex) {
        LOG.error("Caught exception while collecting models in the '" + file + "'", ex);
      }
    });
    modelSourceRootWalker.traverse(sourceRoot);
    if (result.isEmpty()) {
      LOG.warn("Models have not been found within the " + sourceRoot);
    }
    return result;
  }

  @Override
  public boolean canCreateModels() {
    return super.canCreateModels() && !getSourceRoots(SourceRootKinds.SOURCES).isEmpty();
  }

  @Override
  public boolean canCreateModel(@NotNull String modelName) {
    if (!canCreateModels()) {
      return false;
    }

    ModelFactory defaultModelFactory = myModelFactoryRegistry.getDefaultModelFactory(Defaults.DATA_SOURCE_TYPE);
    if (defaultModelFactory == null) {
      return false;
    }

    try {
      // XXX could iterate over all source roots to find the one capable to create a model, but the rest of MR API (namely, createModel) would need
      //     to figure out proper source root as well, which is not a task I'd like to tackle now. I'd use object return value instead of simple
      //     boolean here, which would keep all relevant data (model factory, source root) for model creation
      CompositeResult<DataSource> result = getDataSourceFactoryBridge().create(new SModelName(modelName), Defaults.sourceRoot(this), Defaults.DATA_SOURCE_TYPE);
      return defaultModelFactory.supports(result.getDataSource());
    } catch (NoSourceRootsInModelRootException | DataSourceFactoryNotFoundException | SourceRootDoesNotExistException ignored) {
    }
    return false;
  }

  /**
   * Creates model in the default source root via default factory
   *
   * @see Defaults#sourceRoot
   * @return null if there was IOException
   */
  @Override
  @Nullable
  public SModel createModel(@NotNull String modelName) {
    try {
      return createModel(new SModelName(modelName), null, (DataSourceType) null, null);
    } catch (ModelCannotBeCreatedException e) {
      LOG.error("", e);
      return null;
    }
  }

  /**
   * Creates a new folder-based (per-root by default) model in the default source root.
   *
   * @see Defaults
   * @return null if there was IOException
   */
  @Nullable
  public SModel createPerRootModel(@NotNull String modelName, @Nullable SourceRoot sourceRoot) throws ModelCannotBeCreatedException {
    return createModel(new SModelName(modelName), sourceRoot, PreinstalledDataSourceTypes.MODEL, PreinstalledModelFactoryTypes.PER_ROOT_XML);
  }
  /**
   * Creates a new file based model in the default source root.
   *
   * @see Defaults
   * @return null if there was IOException
   */
  @Nullable
  public SModel createFileModel(@NotNull String modelName, @Nullable SourceRoot sourceRoot) throws ModelCannotBeCreatedException {
    return createModel(new SModelName(modelName), sourceRoot, PreinstalledDataSourceTypes.MPS, PreinstalledModelFactoryTypes.PLAIN_XML);
  }

  /**
   * Creates a new model via given factory with given name and under the provided sourceRoot in this ModelRoot.
   * Whenever the parameter is null the default one is used.
   */
  @NotNull
  public SModel createModel(@NotNull SModelName modelName,
                            @Nullable SourceRoot sourceRoot,
                            @Nullable DataSourceType dataSourceType,
                            @Nullable ModelFactoryType modelFactoryType) throws ModelCannotBeCreatedException {
    if (sourceRoot == null) {
      sourceRoot = Defaults.sourceRoot(this);
    }
    if (modelFactoryType == null) {
      modelFactoryType = Defaults.MODEL_FACTORY_TYPE;
    }
    ModelFactory modelFactory = myModelFactoryRegistry.getFactoryByType(modelFactoryType);
    if (modelFactory == null) {
      throw new ModelFactoryNotFoundException(modelFactoryType);
    }
    if (dataSourceType == null) {
      List<DataSourceType> preferredDataSourceTypes = modelFactory.getPreferredDataSourceTypes();
      if (preferredDataSourceTypes.isEmpty()) {
        dataSourceType = Defaults.DATA_SOURCE_TYPE;
      } else {
        dataSourceType = preferredDataSourceTypes.get(0);
        if (dataSourceType == null) {
          throw new DataSourceTypeIsNullForModelFactoryException(modelFactory);
        }
      }
    }
    DataSourceFactoryFromName dataSourceFactory = myDataSourceRegistry.getFactory(dataSourceType);
    if (dataSourceFactory == null) {
      throw new DataSourceFactoryNotFoundException(dataSourceType);
    }
    return createModel(modelName, sourceRoot, dataSourceFactory, modelFactory);
  }

  /**
   * Creates a new model via given factory with given name and under the provided sourceRoot in this ModelRoot.
   * Whenever the parameter is null the default one is used.
   *
   * The most 'heavy' method (parameter-wise):
   * @param modelName -- controls the name of the new model
   * @param sourceRoot -- the source root to create the new model in
   * @param dataSourceFactory -- data source factory which method {@link DataSourceFactoryFromName#create(SModelName, SourceRoot)}
   *                           is going to be used to create a new data source from the given model name and source root
   * @param modelFactory -- model factory which defines the persisting strategy of the new model.
   *
   * Note that <code>modelFactory</code> is independent enough from the <code>dataSourceFactory</code> and
   *                     data sources it creates.
   * @return new SModel instance with the given name, generated data source lying under the source root,
   *        registered in this model root which is created via the given <code>modelFactory</code>
   * @see Defaults
   */
  @NotNull
  public SModel createModel(@NotNull SModelName modelName,
                            @Nullable SourceRoot sourceRoot,
                            @Nullable DataSourceFactoryFromName dataSourceFactory,
                            @Nullable ModelFactory modelFactory) throws ModelCannotBeCreatedException {
    if (sourceRoot == null) {
      sourceRoot = Defaults.sourceRoot(this);
    }
    if (dataSourceFactory == null) {
      dataSourceFactory = myDataSourceRegistry.getFactory(Defaults.DATA_SOURCE_TYPE);
      if (dataSourceFactory == null) {
        throw new DataSourceFactoryNotFoundException(Defaults.DATA_SOURCE_TYPE);
      }
    }
    if (modelFactory == null) {
      DataSourceType dataSourceType = dataSourceFactory.getType();
      modelFactory = myModelFactoryRegistry.getDefaultModelFactory(dataSourceType);
      if (modelFactory == null) {
        throw new ModelFactoryNotFoundException(dataSourceType);
      }
    }
    CompositeResult<DataSource> result = getDataSourceFactoryBridge().create(modelName, sourceRoot, dataSourceFactory);
    DataSource dataSource = result.getDataSource();
    ModelCreationOptions parameters = result.getOptions();
    if (!modelFactory.supports(dataSource)) {
      throw new FactoryCannotCreateModelException(modelFactory, dataSource);
    }
    return createModel0(modelFactory, dataSource, parameters,true);
  }

  @NotNull
  /*package*/ SModel createModel0(@NotNull ModelFactory modelFactory,
                                  @NotNull DataSource dataSource,
                                  @NotNull ModelCreationOptions parameters,
                                  boolean register) throws ModelCannotBeCreatedException {
    try {
      SModel model = modelFactory.create(dataSource, new SModelName(parameters.getModelName()), parameters.convertToLoadingOptions());
      if (register) {
        registerModel(model);
      }
      return model;
    } catch (IOException | ModelCreationException e) {
      throw new ModelCannotBeCreatedException(e);
    }
  }


  public void rename(FileDataSource dataSource, String newName) throws DataSourceFactoryNotFoundException, NoSourceRootsInModelRootException, SourceRootDoesNotExistException {
    IFile oldFile = dataSource.getFile();
    SourceRoot sourceRoot = findSourceRootOf(oldFile);
    CompositeResult<DataSource> result = getDataSourceFactoryBridge().createFileDataSource(new SModelName(newName), sourceRoot);
    FileDataSource source = (FileDataSource) result.getDataSource();
    IFile newFile = source.getFile();
    if (!newFile.equals(oldFile)) {
      newFile.getParent().mkdirs();
      newFile.createNewFile();
      // at the moment, there's no mechanism to replace model's datasource, hence we replace file of the original source here.
      dataSource.setFile(newFile);
      FileUtil.deleteWithAllEmptyDirs(oldFile);
    }
  }

  private SourceRoot findSourceRootOf(IFile oldFile) {
    List<SourceRoot> sourceRoots = getSourceRoots(SourceRootKinds.SOURCES);
    SourceRoot sourceRoot = sourceRoots.get(0); // first one by default
    for (SourceRoot sourceRoot0 : sourceRoots) {
      if (oldFile.getPath().startsWith(sourceRoot0.getAbsolutePath().getPath())) {
        // using the same sourceRoot
        sourceRoot = sourceRoot0;
        break;
      }
    }
    return sourceRoot;
  }

  @Override
  public void copyTo(@NotNull DefaultModelRoot targetModelRoot) throws CopyNotSupportedException {
    new CopyDefaultModelRootHelper(this, targetModelRoot).copy();
  }

  /**
   * Obviously whilst the model root descriptors are in the <code>AbstractModule</code> we
   * need this method
   */
  @ToRemove(version = 3.6)
  @Deprecated
  public ModelRootDescriptor toDescriptor() {
    ModelRootDescriptor result = new ModelRootDescriptor();
    save(result.getMemento());
    return result;
  }

  /*package*/ ModelFactoryRegistry getModelFactoryRegistry() {
    return myModelFactoryRegistry;
  }

  /*package*/ DataSourceFactoryBridge getDataSourceFactoryBridge() {
    return new DataSourceFactoryBridge(this, myDataSourceRegistry);
  }

  /**
   * Build a descriptor that could be added to a {@link jetbrains.mps.project.structure.modules.ModuleDescriptor} to
   * facilitate instantiation of a model root of this specific type when a module loads.
   *
   * With ModelRootDescriptor/ModuleDescriptor being a mechanism to create/update SModule information, we need a way to construct
   * a descriptor that would end up as DefaultModelRoot. Since there's no relevant API in {@link ModelRootDescriptor} itself (which is
   * questionable btw, provided approach for Language/Generator/Solution module descriptor is different), and exposing Memento keys of
   * this root implementation is bad, these factory methods give an way to construct descriptor for most common scenarios.
   *
   * Present approach is that ModelRootDescriptor controls nothing and accepts plain strings, while objects like ModelRootDescriptor/ModuleDescriptor
   * deal with files. DefaultModelRoot is initialized with MRD and constraints/manipulates low-level persistence data. From that perspective the
   * right way to create ModelRootDescriptor is to configure it with plain strings. OTOH, in many cases we've got IFile already, and it looks odd
   * to go to strings when IFile is handy. Besides, need to be very careful to mangle strings properly to place sourceRoots relative to content root
   * without using IFile/File objects. FIXME Perhaps, need a similar method with String parameters to satisfy both worlds?
   *
   *
   * @param contentRoot root folder for model locations
   * @param modelDir at least one folder (usually under contentRoot; could be equal to it) with model source files
   * @return descriptor for a default model root
   */
  @NotNull
  public static ModelRootDescriptor createDescriptor(@NotNull IFile contentRoot, final IFile ... modelDir) {
    if (modelDir.length == 0) {
      throw new IllegalArgumentException("Please specify at least one source root (could be same as contentRoot)");
    }
    // XXX proper implementation shall do what save() method does without need to instantiate DefaultModelRoot
    DefaultModelRoot result = new DefaultModelRoot(0);
    result.setContentDirectory(contentRoot);
    class SourceRootPrim implements SourceRoot {
      private final IFile myModelDir;

      SourceRootPrim(IFile modelRoot) {
        myModelDir = modelRoot;
      }

      @NotNull
      @Override
      public String getPath() {
        return myModelDir.getPath();
      }

      @NotNull
      @Override
      public IFile getAbsolutePath() {
        return myModelDir;
      }
    }
    for (IFile md : modelDir) {
      result.addSourceRoot(SourceRootKinds.SOURCES, new SourceRootPrim(md));
    }
    return result.toDescriptor();
  }

  /**
   * Same as {@link #createDescriptor(IFile, IFile...)} limited to a single location with source model files
   * @param modelDir folder with model source files, serves both as content root and as a source location
   * @return descriptor for a default model root
   */
  @NotNull
  public static ModelRootDescriptor createSingleFolderDescriptor(@NotNull final IFile modelDir) {
    DefaultModelRoot result = new DefaultModelRoot(0);
    result.setContentDirectory(modelDir);
    result.addSourceRoot(SourceRootKinds.SOURCES, new DefaultSourceRoot("", modelDir));
    return result.toDescriptor();
  }

  static final class Defaults {
    /*package*/ static final DataSourceType DATA_SOURCE_TYPE = PreinstalledDataSourceTypes.MPS;
    /*package*/ static final ModelFactoryType MODEL_FACTORY_TYPE = PreinstalledModelFactoryTypes.PLAIN_XML;

    /**
     * @return first source root as a default one
     * @throws NoSourceRootsInModelRootException if there are no source roots here
     */
    @NotNull
    static SourceRoot sourceRoot(@NotNull FileBasedModelRoot modelRoot) throws NoSourceRootsInModelRootException {
      List<SourceRoot> sourceRoots = modelRoot.getSourceRoots(SourceRootKinds.SOURCES);
      if (sourceRoots.isEmpty()) {
        throw new NoSourceRootsInModelRootException(modelRoot);
      }
      return sourceRoots.get(0);
    }
  }
}

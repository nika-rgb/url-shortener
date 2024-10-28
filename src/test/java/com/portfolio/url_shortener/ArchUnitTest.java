package com.portfolio.url_shortener;

import com.tngtech.archunit.core.importer.ImportOption;
import com.tngtech.archunit.junit.AnalyzeClasses;
import com.tngtech.archunit.junit.ArchTest;
import com.tngtech.archunit.lang.ArchRule;

import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.classes;
import static com.tngtech.archunit.library.Architectures.layeredArchitecture;

@AnalyzeClasses(packages = "com.portfolio.url_shortener", importOptions = ImportOption.DoNotIncludeTests.class)
class CleanArchitectureTest {

    final static String presentationLayer = "Presentation";
    final static String useCaseLayer = "UseCase";
    final static String domainLayer = "Domain";

    @ArchTest
    static final ArchRule DEPENDENCY_LAYERS_ARE_RESPECTED = layeredArchitecture()
            .consideringAllDependencies()
            .layer(presentationLayer).definedBy("com.portfolio.url_shortener.*.api.v1..")
            .layer(useCaseLayer).definedBy("com.portfolio.url_shortener.*.application..")
            .layer(domainLayer).definedBy("com.portfolio.url_shortener.*.domain..")

            .whereLayer(presentationLayer).mayNotBeAccessedByAnyLayer()
            .whereLayer(useCaseLayer).mayOnlyBeAccessedByLayers(presentationLayer)
            .whereLayer(domainLayer).mayOnlyBeAccessedByLayers(useCaseLayer, presentationLayer);

    @ArchTest
    static final ArchRule USE_CASE_CLASSES_ARE_ANNOTATED_CORRECTLY = classes()
            .that()
            .resideInAPackage("com.portfolio.url_shortener.*.application..")
            .and()
            .haveSimpleNameEndingWith("UseCase")
            .should()
            .beAnnotatedWith(UseCase.class);

    @ArchTest
    static final ArchRule ONLY_USE_CASE_CLASSES_ARE_ANNOTATED_WITH_USE_CASE_ANNOTATION = classes()
            .that()
            .areAnnotatedWith(UseCase.class)
            .should()
            .haveSimpleNameEndingWith("UseCase");

}
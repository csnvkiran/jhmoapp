package org.klea.jhmoapp;

import com.tngtech.archunit.core.domain.JavaClasses;
import com.tngtech.archunit.core.importer.ClassFileImporter;
import com.tngtech.archunit.core.importer.ImportOption;
import org.junit.jupiter.api.Test;

import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.noClasses;

class ArchTest {

    @Test
    void servicesAndRepositoriesShouldNotDependOnWebLayer() {

        JavaClasses importedClasses = new ClassFileImporter()
            .withImportOption(ImportOption.Predefined.DO_NOT_INCLUDE_TESTS)
            .importPackages("org.klea.jhmoapp");

        noClasses()
            .that()
                .resideInAnyPackage("org.klea.jhmoapp.service..")
            .or()
                .resideInAnyPackage("org.klea.jhmoapp.repository..")
            .should().dependOnClassesThat()
                .resideInAnyPackage("..org.klea.jhmoapp.web..")
        .because("Services and repositories should not depend on web layer")
        .check(importedClasses);
    }
}

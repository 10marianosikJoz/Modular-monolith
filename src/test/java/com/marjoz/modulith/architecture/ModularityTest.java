package com.marjoz.modulith.architecture;

import com.marjoz.modulith.ModulithApplication;
import org.junit.jupiter.api.Test;
import org.springframework.modulith.core.ApplicationModules;
import org.springframework.modulith.docs.Documenter;

class ModularityTest {

    private final ApplicationModules modules = ApplicationModules.of(ModulithApplication.class);

    @Test
    void verifyModulesStructure() {
        modules.verify();
    }

    @Test
    void createArchitectureDocumentation() {
        new Documenter(modules).writeDocumentation();
    }
}
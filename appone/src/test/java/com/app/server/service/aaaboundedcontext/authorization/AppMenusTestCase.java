package com.app.server.service.aaaboundedcontext.authorization;
import com.app.server.service.EntityTestCriteria;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import com.app.config.WebConfigExtended;
import org.springframework.test.context.ContextConfiguration;
import org.junit.FixMethodOrder;
import org.junit.runners.MethodSorters;
import org.springframework.test.context.TestExecutionListeners;
import com.app.server.repository.aaaboundedcontext.authorization.AppMenusRepository;
import com.app.shared.aaaboundedcontext.authorization.AppMenus;
import org.springframework.beans.factory.annotation.Autowired;
import com.athena.framework.server.helper.RuntimeLogInfoHelper;
import com.athena.framework.server.helper.EntityValidatorHelper;
import com.athena.framework.server.test.RandomValueGenerator;
import java.util.HashMap;
import java.util.List;
import com.spartan.healthmeter.entity.scheduler.ArtMethodCallStack;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;
import org.junit.Before;
import org.junit.After;
import com.athena.framework.shared.entity.web.entityInterface.CommonEntityInterface.RECORD_TYPE;
import org.junit.Test;
import com.athena.framework.server.exception.biz.SpartanConstraintViolationException;
import com.athena.framework.server.exception.biz.SpartanIncorrectDataException;
import com.athena.framework.server.exception.repository.SpartanPersistenceException;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(classes = WebConfigExtended.class)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
@TestExecutionListeners({ org.springframework.test.context.support.DependencyInjectionTestExecutionListener.class, org.springframework.test.context.support.DirtiesContextTestExecutionListener.class, org.springframework.test.context.transaction.TransactionalTestExecutionListener.class })
public class AppMenusTestCase extends EntityTestCriteria {

    @Autowired
    private AppMenusRepository<AppMenus> appmenusRepository;

    @Autowired
    private RuntimeLogInfoHelper runtimeLogInfoHelper;

    @Autowired
    private EntityValidatorHelper<Object> entityValidator;

    private RandomValueGenerator valueGenerator = new RandomValueGenerator();

    private static HashMap<String, Object> map = new HashMap<String, Object>();

    private static List<EntityTestCriteria> entityContraint;

    @Autowired
    private ArtMethodCallStack methodCallStack;

    protected MockHttpSession session;

    protected MockHttpServletRequest request;

    protected MockHttpServletResponse response;

    protected void startSession() {
        session = new MockHttpSession();
    }

    protected void endSession() {
        session.clearAttributes();
        session.invalidate();
        session = null;
    }

    protected void startRequest() {
        request = new MockHttpServletRequest();
        request.setSession(session);
        org.springframework.web.context.request.RequestContextHolder.setRequestAttributes(new org.springframework.web.context.request.ServletRequestAttributes(request));
    }

    protected void endRequest() {
        ((org.springframework.web.context.request.ServletRequestAttributes) org.springframework.web.context.request.RequestContextHolder.getRequestAttributes()).requestCompleted();
        org.springframework.web.context.request.RequestContextHolder.resetRequestAttributes();
        request = null;
    }

    @Before
    public void before() {
        startSession();
        startRequest();
        setBeans();
    }

    @After
    public void after() {
        endSession();
        endRequest();
    }

    private void setBeans() {
        runtimeLogInfoHelper.createRuntimeLogUserInfo(1, "AAAAA", request.getRemoteHost());
        org.junit.Assert.assertNotNull(runtimeLogInfoHelper);
        methodCallStack.setRequestId(java.util.UUID.randomUUID().toString().toUpperCase());
        entityContraint = addingListOfFieldForNegativeTesting();
    }

    private AppMenus createAppMenus(Boolean isSave) throws SpartanPersistenceException, SpartanConstraintViolationException {
        AppMenus appmenus = new AppMenus();
        appmenus.setMenuAccessRights(5);
        appmenus.setMenuTreeId("P629viRWzbQIlbORbVK7efg5MZcJKGAufsDo89WVjsepbxryFL");
        appmenus.setAppId("uzozakcfb6S4OCIPfbML5Pr7OAbsxQj9Z5wcz8wrTOrbZI51Mw");
        appmenus.setRefObjectId("wilzW4VlMb7r45JtdztNiLfBJlyT4Hy2Uh6zladuYrRamKU8xu");
        appmenus.setMenuCommands("Lu1Nz8giUrjv3xmjNOVeIif41npKPFaaqLf5lDxsQUw3besyEn");
        appmenus.setAppType(1);
        appmenus.setMenuHead(true);
        appmenus.setMenuLabel("Tt0MyVkgMjGgpo93h1rCCQbleD87tBMoxNb64vgmlS4A5RzxoI");
        appmenus.setAutoSave(true);
        appmenus.setMenuAction("RWq22TGSxnYjrbNAR7j8sTHtl2p7SczMZGCSS8qssDQh6XjcWa");
        appmenus.setMenuIcon("E15itsZv9dus3WPMqDX9k1O9Cxn4rsrI2bc23t5yrGuhfuMtRy");
        appmenus.setUiType("H4T");
        appmenus.setMenuDisplay(true);
        appmenus.setEntityValidator(entityValidator);
        return appmenus;
    }

    @Test
    public void test1Save() {
        try {
            AppMenus appmenus = createAppMenus(true);
            appmenus.setEntityAudit(1, "xyz", RECORD_TYPE.ADD);
            appmenus.isValid();
            appmenusRepository.save(appmenus);
            map.put("AppMenusPrimaryKey", appmenus._getPrimarykey());
        } catch (com.athena.framework.server.exception.biz.SpartanConstraintViolationException e) {
            org.junit.Assert.fail(e.getMessage());
        } catch (java.lang.Exception e) {
            org.junit.Assert.fail(e.getMessage());
        }
    }

    @Test
    public void test2Update() {
        try {
            org.junit.Assert.assertNotNull(map.get("AppMenusPrimaryKey"));
            AppMenus appmenus = appmenusRepository.findById((java.lang.String) map.get("AppMenusPrimaryKey"));
            appmenus.setMenuAccessRights(3);
            appmenus.setVersionId(1);
            appmenus.setMenuTreeId("CBsOKbUdwXYtMAzVH5W6cNOrOgYf6pZcPsvI7Rzpw8jQlsHtBu");
            appmenus.setAppId("mTs0KPjAQ9APtF4NnCIen8zjv3z9nO2LIeFlsRnSmkKhoEUXRr");
            appmenus.setRefObjectId("yHSYHLRC0yRaaOnljXl763BuVar3HzXK8D0Fc4CNTuPp9SszPI");
            appmenus.setMenuCommands("hLCzZ948LhBjg03JccRNDuLQRxrGovk8dbYfvqhqilDp95BO4l");
            appmenus.setAppType(1);
            appmenus.setMenuLabel("6EjvHxn21GQJcM12KyUs0LMzvPZ5sBQKbba7VgpUE0wcIMlMKK");
            appmenus.setMenuAction("ltKIybvPdtDItZpMH9gQU2re1Z36G59cFIFmUJ3L2y32i6sv1Z");
            appmenus.setMenuIcon("HZQwBae0qx4jbnocRcAEAZvl5J5TAClxmIlW3FMPd2bDwxZxuX");
            appmenus.setUiType("gJq");
            appmenus.setEntityAudit(1, "xyz", RECORD_TYPE.UPDATE);
            appmenusRepository.update(appmenus);
        } catch (com.athena.framework.server.exception.repository.SpartanPersistenceException e) {
            org.junit.Assert.fail(e.getMessage());
        } catch (java.lang.Exception e) {
            org.junit.Assert.fail(e.getMessage());
        }
    }

    @Test
    public void test3FindById() {
        try {
            org.junit.Assert.assertNotNull(map.get("AppMenusPrimaryKey"));
            appmenusRepository.findById((java.lang.String) map.get("AppMenusPrimaryKey"));
        } catch (com.athena.framework.server.exception.repository.SpartanPersistenceException e) {
            org.junit.Assert.fail(e.getMessage());
        } catch (Exception e) {
            org.junit.Assert.fail(e.getMessage());
        }
    }

    @Test
    public void test6Delete() {
        try {
            org.junit.Assert.assertNotNull(map.get("AppMenusPrimaryKey"));
            appmenusRepository.delete((java.lang.String) map.get("AppMenusPrimaryKey"));
        } catch (com.athena.framework.server.exception.repository.SpartanPersistenceException e) {
            org.junit.Assert.fail(e.getMessage());
        } catch (Exception e) {
            org.junit.Assert.fail(e.getMessage());
        }
    }

    private void validateAppMenus(EntityTestCriteria contraints, AppMenus appmenus) throws SpartanIncorrectDataException, SpartanConstraintViolationException, SpartanPersistenceException {
        if (contraints.getRuleType() == MIN_MAX) {
            appmenus.isValid();
        } else if (contraints.getRuleType() == NOT_NULL) {
            appmenus.isValid();
        } else if (contraints.getRuleType() == REGEX) {
            appmenus.isValid();
        } else if (contraints.getRuleType() == UNIQUE) {
            appmenusRepository.save(appmenus);
        }
    }

    private List<EntityTestCriteria> addingListOfFieldForNegativeTesting() {
        List<EntityTestCriteria> entityContraints = new java.util.ArrayList<EntityTestCriteria>();
        entityContraints.add(new EntityTestCriteria(NOT_NULL, 1, "menuTreeId", null));
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 2, "menuTreeId", "5WawAVVZ7CVMPzdo2Ang8bEZqinkEEcrP2hIhmwNeM9kvu35NkNZWcsgApbVyQCM0sdIVe89Li38sDmvnrV7K4AFjuuhIVtrCItxJ0RIGgKRzsSMHGO4dTypMFM0PBZxEBjPSgqylCKon3nqhI9iDIYuAV8wVkPowBdhS2kTEOU9LAhaQ8UE5zr4REGlX2daVAaCDqThp8FzS5YAq8ztkK8TC9H9NNCJv3zUQ6iOS5ZbTsd5KVnCpNVUUz00Maurg"));
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 3, "menuIcon", "dbswMdbMTtDrK536ZnkqUmNhGVzo1ZRdG6dpU6R33YOtDLmLShpKu6A8J1LEGkzkBadbDWQ6OEtSF6NNi29qj9evZSIeN4MihUTJTn6uqPOxuZz0zM5XvjXAnYhMYZ6HHrccoaBZuic7sWVucT1ZIRxJmgYnFZHbErgj2Cnchya4EOsJbRF08Ibh1LO3p7oaVWOI2hX0ITJFSJ7fD6zXicTcbHiMmdhtkoJIS3XfExAi2BhU34y12rjFIHP9Ek0oy"));
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 4, "menuAction", "x6poEDfOb984ZLJhF7NogOErg5D6EhK8D30K58kdikBCpks6zlQw3QBPODSpRxbHbdLOxcMJiOihq8C4i2SEM1j4qac9ckroMLffORSreyI5CH9r7jFdqkYnCOi3kaaG4JVTQyFGnVSRjtftgH0rs9lEeKIXPcB9vEeRtxFFBCQYllhEESo2YGqu4reNx2oQJHe1SMpn7bWteGbVMr468S9ZSAb2zY2eYCDHioSABmr0hNMUyHANzCSf6DLInTQLC"));
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 5, "menuCommands", "8PnvLADn8A8xKJA20Fd3J8x5oz8EfUnKE6SXdoD51JrxjOKzfUB1uzT0MrNyAcbMS"));
        entityContraints.add(new EntityTestCriteria(NOT_NULL, 6, "menuDisplay", null));
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 7, "menuDisplay", true));
        entityContraints.add(new EntityTestCriteria(NOT_NULL, 8, "menuHead", null));
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 9, "menuHead", true));
        entityContraints.add(new EntityTestCriteria(NOT_NULL, 10, "menuLabel", null));
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 11, "menuLabel", "3ntZa99rQQK4RlaL8z9w4NlxpcNfcXrJDyQ54Kn4cukwU0Uy1DNU0GUF4oOm8Y6wbFql9Ycr2hHlNXzBIDUVDTjyEz1rA1JyaCLKpWZRtUIpKlpN9lPZtkc9j2LuOepjlVFy3rk7Zwv7nF36hicmkldNSNhAAGtHoPxrnW5BpLmNIeizbAZgHDjtse21a6PnUYK3dOe8tpH5TLojuTBlFgbxGNRfZRNnLsMn3n9B2BRWJGBX9ViUN8FYRcDLQS8lX"));
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 12, "uiType", "VBHx"));
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 13, "refObjectId", "wMokuFaJgXsoGI0E9HxfeeJdIHFPwOtr08gCED958CmSlwWcY3E1fU9RectHLxScklcnicqgHUtjWVUpF9ctBHq9Wfpvf2QQLy0kwELUyOeu3b4h5xA40FcZH7T0DYxiim5eHVOtHq4M8wWDBKsjSCp4yrLnIys8NTkKLytGZrOI1PFvsSDDEYEm857BfKPGhOPNzeB5YcfYVaIUaCGousLPawHPSg6tFaxKIJ01af11n1cOAnEtUUC7uzf465EZG"));
        entityContraints.add(new EntityTestCriteria(NOT_NULL, 14, "menuAccessRights", null));
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 15, "menuAccessRights", 15));
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 16, "appType", 4));
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 17, "appId", "TLAIjuIt7sgBwxkwjJVyCSKne7TBUXphDie51KEp1zEqnVuW1B7uTV08DDinVb0WPU9ErkhBVDkBuwYCssmxhFV8f3sU22Am8vpLbAtbDDdKdIsAg3dJYdZgTGxter7QKZhpU5C8eybCqSC3xuWNqzqErs00zRLZ1L58xz4XTA2gPHWbWJL6ZFcvINvqlhu0IDikSeCwAEKWwHulrX2XpbBq70N0rnUGEvEDrHoQDThEJAMQVRjPNQNokoahA8F5t"));
        entityContraints.add(new EntityTestCriteria(NOT_NULL, 18, "autoSave", null));
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 19, "autoSave", true));
        return entityContraints;
    }

    @Test
    public void test5NegativeTesting() throws NoSuchMethodException, SecurityException, IllegalArgumentException, IllegalAccessException, NoSuchFieldException, Exception, SpartanPersistenceException {
        int failureCount = 0;
        for (EntityTestCriteria contraints : this.entityContraint) {
            try {
                AppMenus appmenus = createAppMenus(false);
                java.lang.reflect.Field field = null;
                if (!contraints.getFieldName().equalsIgnoreCase("CombineUniqueKey")) {
                    field = appmenus.getClass().getDeclaredField(contraints.getFieldName());
                }
                switch(((contraints.getTestId()))) {
                    case 0:
                        break;
                    case 1:
                        field.setAccessible(true);
                        field.set(appmenus, null);
                        validateAppMenus(contraints, appmenus);
                        failureCount++;
                        break;
                    case 2:
                        appmenus.setMenuTreeId(contraints.getNegativeValue().toString());
                        validateAppMenus(contraints, appmenus);
                        failureCount++;
                        break;
                    case 3:
                        appmenus.setMenuIcon(contraints.getNegativeValue().toString());
                        validateAppMenus(contraints, appmenus);
                        failureCount++;
                        break;
                    case 4:
                        appmenus.setMenuAction(contraints.getNegativeValue().toString());
                        validateAppMenus(contraints, appmenus);
                        failureCount++;
                        break;
                    case 5:
                        appmenus.setMenuCommands(contraints.getNegativeValue().toString());
                        validateAppMenus(contraints, appmenus);
                        failureCount++;
                        break;
                    case 6:
                        field.setAccessible(true);
                        field.set(appmenus, null);
                        validateAppMenus(contraints, appmenus);
                        failureCount++;
                        break;
                    case 7:
                        break;
                    case 8:
                        field.setAccessible(true);
                        field.set(appmenus, null);
                        validateAppMenus(contraints, appmenus);
                        failureCount++;
                        break;
                    case 9:
                        break;
                    case 10:
                        field.setAccessible(true);
                        field.set(appmenus, null);
                        validateAppMenus(contraints, appmenus);
                        failureCount++;
                        break;
                    case 11:
                        appmenus.setMenuLabel(contraints.getNegativeValue().toString());
                        validateAppMenus(contraints, appmenus);
                        failureCount++;
                        break;
                    case 12:
                        appmenus.setUiType(contraints.getNegativeValue().toString());
                        validateAppMenus(contraints, appmenus);
                        failureCount++;
                        break;
                    case 13:
                        appmenus.setRefObjectId(contraints.getNegativeValue().toString());
                        validateAppMenus(contraints, appmenus);
                        failureCount++;
                        break;
                    case 14:
                        field.setAccessible(true);
                        field.set(appmenus, null);
                        validateAppMenus(contraints, appmenus);
                        failureCount++;
                        break;
                    case 15:
                        appmenus.setMenuAccessRights(Integer.parseInt(contraints.getNegativeValue().toString()));
                        validateAppMenus(contraints, appmenus);
                        failureCount++;
                        break;
                    case 16:
                        appmenus.setAppType(Integer.parseInt(contraints.getNegativeValue().toString()));
                        validateAppMenus(contraints, appmenus);
                        failureCount++;
                        break;
                    case 17:
                        appmenus.setAppId(contraints.getNegativeValue().toString());
                        validateAppMenus(contraints, appmenus);
                        failureCount++;
                        break;
                    case 18:
                        field.setAccessible(true);
                        field.set(appmenus, null);
                        validateAppMenus(contraints, appmenus);
                        failureCount++;
                        break;
                    case 19:
                        break;
                }
            } catch (SpartanIncorrectDataException e) {
                e.printStackTrace();
            } catch (SpartanConstraintViolationException e) {
                e.printStackTrace();
            } catch (SpartanPersistenceException e) {
                e.printStackTrace();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        if (failureCount > 0) {
            org.junit.Assert.fail();
        }
    }
}
